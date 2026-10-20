package com.comunidadmascotera.services;

import com.comunidadmascotera.entities.Animal;
import com.comunidadmascotera.entities.SolicitudAdopcion;
import com.comunidadmascotera.repositories.AnimalRepository;
import com.comunidadmascotera.repositories.SolicitudAdopcionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Transactional
public class AdopcionService {

    private final SolicitudAdopcionRepository solicitudRepository;
    private final AnimalRepository animalRepository;

    public AdopcionService(SolicitudAdopcionRepository solicitudRepository, AnimalRepository animalRepository) {
        this.solicitudRepository = solicitudRepository;
        this.animalRepository = animalRepository;
    }

    public List<SolicitudAdopcion> listarTodas() {
        return solicitudRepository.findAll();
    }

    public List<SolicitudAdopcion> listarPorAnimal(String animalId) {
        return solicitudRepository.findByAnimalId(animalId);
    }

    public SolicitudAdopcion registrarSolicitud(String animalId, SolicitudAdopcion solicitud) {
        Animal animal = animalRepository.findById(animalId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Animal no encontrado con ID: " + animalId));
        solicitud.setAnimal(animal);
        return solicitudRepository.save(solicitud);
    }

    public SolicitudAdopcion cambiarEstado(Long solicitudId, String nuevoEstado) {
        SolicitudAdopcion solicitud = solicitudRepository.findById(solicitudId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Solicitud no encontrada con ID: " + solicitudId));
        solicitud.setStatus(nuevoEstado);
        return solicitudRepository.save(solicitud);
    }
}
