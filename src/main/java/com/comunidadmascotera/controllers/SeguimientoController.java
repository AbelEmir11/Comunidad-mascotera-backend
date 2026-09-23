package com.comunidadmascotera.controllers;

import com.comunidadmascotera.entities.HitoSeguimiento;
import com.comunidadmascotera.entities.SeguimientoMascota;
import com.comunidadmascotera.services.SeguimientoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/seguimiento")
public class SeguimientoController {

    private final SeguimientoService seguimientoService;

    public SeguimientoController(SeguimientoService seguimientoService) {
        this.seguimientoService = seguimientoService;
    }

    @GetMapping
    public List<SeguimientoMascota> listar() {
        return seguimientoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SeguimientoMascota> obtenerPorId(@PathVariable String id) {
        return seguimientoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/mascota/{petId}")
    public ResponseEntity<SeguimientoMascota> obtenerPorMascota(@PathVariable String petId) {
        return seguimientoService.buscarPorMascota(petId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Endpoint para que el adoptante complete una entrega del Álbum de Vida
     * con foto y anécdota, desbloqueando medalla y actualizando el semáforo del refugio.
     */
    @PostMapping("/hitos/{hitoId}/completar")
    public ResponseEntity<HitoSeguimiento> completarHito(
            @PathVariable String hitoId,
            @RequestBody Map<String, String> payload) {
        String photoUrl = payload.getOrDefault("photoUrl", "https://images.unsplash.com/photo-1543466835-00a7907e9de1?auto=format&fit=crop&w=600&q=80");
        String notes = payload.getOrDefault("notes", "Reporte de adaptación al día.");

        HitoSeguimiento completado = seguimientoService.completarHito(hitoId, photoUrl, notes);
        return ResponseEntity.ok(completado);
    }
}
