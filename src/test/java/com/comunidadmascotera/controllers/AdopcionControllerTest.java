package com.comunidadmascotera.controllers;

import com.comunidadmascotera.entities.Animal;
import com.comunidadmascotera.entities.SolicitudAdopcion;
import com.comunidadmascotera.exceptions.GlobalExceptionHandler;
import com.comunidadmascotera.services.AdopcionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.server.ResponseStatusException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Pruebas para el endpoint POST /api/adopciones/postular/{animalId}
 * Verifican:
 * 1. Postulación válida con animal existente -> 201 CREATED con datos guardados.
 * 2. Postulación con datos inválidos (DNI/teléfono/nombre) -> 400 BAD REQUEST sin persistir.
 * 3. Postulación con animal inexistente -> 404 NOT FOUND con mensaje comprensible.
 */
@WebMvcTest(AdopcionController.class)
@Import(GlobalExceptionHandler.class)
class AdopcionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private AdopcionService adopcionService;

    @Test
    @DisplayName("Debe registrar postulación válida y retornar HTTP 201 Created con el registro guardado")
    void postular_conDatosValidosYAnimalExistente_retornaCreated() throws Exception {
        String animalId = "pet-1";

        SolicitudAdopcion solicitudGuardada = new SolicitudAdopcion();
        solicitudGuardada.setId(10L);
        solicitudGuardada.setApplicantName("Laura Martínez");
        solicitudGuardada.setApplicantDni("38123456");
        solicitudGuardada.setApplicantPhone("2615554321");
        solicitudGuardada.setApplicantHousing("casa_patio");
        solicitudGuardada.setAgreedToFollowup(true);
        solicitudGuardada.setStatus("PENDIENTE");

        Animal animal = new Animal();
        animal.setId(animalId);
        animal.setName("Pampa");
        solicitudGuardada.setAnimal(animal);

        when(adopcionService.registrarSolicitud(eq(animalId), any(SolicitudAdopcion.class)))
                .thenReturn(solicitudGuardada);

        String jsonBody = """
            {
                "applicantName": "Laura Martínez",
                "applicantDni": "38123456",
                "applicantPhone": "2615554321",
                "applicantHousing": "casa_patio",
                "agreedToFollowup": true
            }
        """;

        mockMvc.perform(post("/api/adopciones/postular/{animalId}", animalId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(10))
                .andExpect(jsonPath("$.applicantName").value("Laura Martínez"))
                .andExpect(jsonPath("$.applicantDni").value("38123456"))
                .andExpect(jsonPath("$.applicantPhone").value("2615554321"))
                .andExpect(jsonPath("$.status").value("PENDIENTE"))
                .andExpect(jsonPath("$.animal.id").value("pet-1"));

        verify(adopcionService).registrarSolicitud(eq(animalId), any(SolicitudAdopcion.class));
    }

    @Test
    @DisplayName("Debe rechazar postulación con DNI inválido y retornar HTTP 400 Bad Request")
    void postular_conDniInvalido_retornaBadRequest() throws Exception {
        String animalId = "pet-1";

        // DNI con solo 4 dígitos (inválido, requiere exactamente 8 dígitos)
        String jsonBody = """
            {
                "applicantName": "Laura Martínez",
                "applicantDni": "1234",
                "applicantPhone": "2615554321",
                "applicantHousing": "casa_patio",
                "agreedToFollowup": true
            }
        """;

        mockMvc.perform(post("/api/adopciones/postular/{animalId}", animalId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.fieldErrors.applicantDni").exists());

        verify(adopcionService, never()).registrarSolicitud(any(), any());
    }

    @Test
    @DisplayName("Debe rechazar postulación con teléfono inválido y retornar HTTP 400 Bad Request")
    void postular_conTelefonoInvalido_retornaBadRequest() throws Exception {
        String animalId = "pet-1";

        // Teléfono con menos de 8 dígitos
        String jsonBody = """
            {
                "applicantName": "Laura Martínez",
                "applicantDni": "38123456",
                "applicantPhone": "123",
                "applicantHousing": "casa_patio",
                "agreedToFollowup": true
            }
        """;

        mockMvc.perform(post("/api/adopciones/postular/{animalId}", animalId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.fieldErrors.applicantPhone").exists());

        verify(adopcionService, never()).registrarSolicitud(any(), any());
    }

    @Test
    @DisplayName("Debe rechazar postulación con nombre vacío y retornar HTTP 400 Bad Request")
    void postular_conNombreVacio_retornaBadRequest() throws Exception {
        String animalId = "pet-1";

        String jsonBody = """
            {
                "applicantName": "",
                "applicantDni": "38123456",
                "applicantPhone": "2615554321",
                "applicantHousing": "casa_patio",
                "agreedToFollowup": true
            }
        """;

        mockMvc.perform(post("/api/adopciones/postular/{animalId}", animalId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.fieldErrors.applicantName").exists());

        verify(adopcionService, never()).registrarSolicitud(any(), any());
    }

    @Test
    @DisplayName("Debe retornar HTTP 404 Not Found cuando el animal no existe")
    void postular_conAnimalInexistente_retornaNotFound() throws Exception {
        String animalInexistenteId = "pet-inexistente";

        when(adopcionService.registrarSolicitud(eq(animalInexistenteId), any(SolicitudAdopcion.class)))
                .thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND, "Animal no encontrado con ID: " + animalInexistenteId));

        String jsonBody = """
            {
                "applicantName": "Laura Martínez",
                "applicantDni": "38123456",
                "applicantPhone": "2615554321",
                "applicantHousing": "casa_patio",
                "agreedToFollowup": true
            }
        """;

        mockMvc.perform(post("/api/adopciones/postular/{animalId}", animalInexistenteId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.message").value("Animal no encontrado con ID: pet-inexistente"));

        verify(adopcionService).registrarSolicitud(eq(animalInexistenteId), any(SolicitudAdopcion.class));
    }
}
