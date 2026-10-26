package com.comunidadmascotera.services;

import com.comunidadmascotera.entities.Animal;
import com.comunidadmascotera.entities.SolicitudAdopcion;
import com.comunidadmascotera.repositories.AnimalRepository;
import com.comunidadmascotera.repositories.SolicitudAdopcionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Pruebas unitarias para AdopcionService
 * Verifica la lógica de negocio y persistencia en la capa de servicio:
 * 1. Persistencia exitosa asociando el animal existente.
 * 2. Lanzamiento de ResponseStatusException (404) si el animal no existe.
 */
@ExtendWith(MockitoExtension.class)
class AdopcionServiceTest {

    @Mock
    private SolicitudAdopcionRepository solicitudRepository;

    @Mock
    private AnimalRepository animalRepository;

    @InjectMocks
    private AdopcionService adopcionService;

    private Animal animalMock;
    private SolicitudAdopcion solicitudMock;

    @BeforeEach
    void setUp() {
        animalMock = new Animal();
        animalMock.setId("pet-1");
        animalMock.setName("Pampa");

        solicitudMock = new SolicitudAdopcion();
        solicitudMock.setApplicantName("Laura Martínez");
        solicitudMock.setApplicantDni("38123456");
        solicitudMock.setApplicantPhone("2615554321");
        solicitudMock.setApplicantHousing("casa_patio");
        solicitudMock.setAgreedToFollowup(true);
    }

    @Test
    @DisplayName("Debe buscar el animal, asociarlo y persistir la solicitud cuando el animal existe")
    void registrarSolicitud_animalExiste_asociaYGuarda() {
        when(animalRepository.findById("pet-1")).thenReturn(Optional.of(animalMock));
        when(solicitudRepository.save(any(SolicitudAdopcion.class))).thenAnswer(invocation -> {
            SolicitudAdopcion s = invocation.getArgument(0);
            s.setId(1L);
            return s;
        });

        SolicitudAdopcion resultado = adopcionService.registrarSolicitud("pet-1", solicitudMock);

        assertThat(resultado).isNotNull();
        assertThat(resultado.getId()).isEqualTo(1L);
        assertThat(resultado.getAnimal()).isEqualTo(animalMock);
        verify(animalRepository).findById("pet-1");
        verify(solicitudRepository).save(solicitudMock);
    }

    @Test
    @DisplayName("Debe lanzar ResponseStatusException NOT_FOUND (404) cuando el animal no existe")
    void registrarSolicitud_animalNoExiste_lanzaResponseStatusException() {
        when(animalRepository.findById("pet-inexistente")).thenReturn(Optional.empty());

        assertThatThrownBy(() -> adopcionService.registrarSolicitud("pet-inexistente", solicitudMock))
                .isInstanceOf(ResponseStatusException.class)
                .hasFieldOrPropertyWithValue("statusCode", HttpStatus.NOT_FOUND)
                .hasMessageContaining("Animal no encontrado con ID: pet-inexistente");

        verify(animalRepository).findById("pet-inexistente");
        verify(solicitudRepository, never()).save(any());
    }
}
