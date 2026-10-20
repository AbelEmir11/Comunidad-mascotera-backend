package com.comunidadmascotera.controllers;

import com.comunidadmascotera.entities.SolicitudAdopcion;
import com.comunidadmascotera.services.AdopcionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/adopciones")
public class AdopcionController {

    private final AdopcionService adopcionService;

    public AdopcionController(AdopcionService adopcionService) {
        this.adopcionService = adopcionService;
    }

    @GetMapping
    public List<SolicitudAdopcion> listar() {
        return adopcionService.listarTodas();
    }

    @GetMapping("/animal/{animalId}")
    public List<SolicitudAdopcion> listarPorAnimal(@PathVariable String animalId) {
        return adopcionService.listarPorAnimal(animalId);
    }

    @PostMapping("/postular/{animalId}")
    public ResponseEntity<SolicitudAdopcion> postular(
            @PathVariable String animalId,
            @Valid @RequestBody SolicitudAdopcion solicitud) {
        SolicitudAdopcion guardada = adopcionService.registrarSolicitud(animalId, solicitud);
        return ResponseEntity.status(HttpStatus.CREATED).body(guardada);
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<SolicitudAdopcion> cambiarEstado(
            @PathVariable Long id,
            @RequestParam String estado) {
        return ResponseEntity.ok(adopcionService.cambiarEstado(id, estado));
    }
}
