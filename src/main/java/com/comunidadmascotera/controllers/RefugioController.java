package com.comunidadmascotera.controllers;

import com.comunidadmascotera.entities.Refugio;
import com.comunidadmascotera.services.RefugioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/refugios")
public class RefugioController {

    private final RefugioService refugioService;

    public RefugioController(RefugioService refugioService) {
        this.refugioService = refugioService;
    }

    @GetMapping
    public List<Refugio> listar(@RequestParam(required = false) String zona) {
        if (zona != null && !zona.isBlank()) {
            return refugioService.buscarPorZona(zona);
        }
        return refugioService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Refugio> obtenerPorId(@PathVariable String id) {
        return refugioService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Refugio> crear(@RequestBody Refugio refugio) {
        return ResponseEntity.ok(refugioService.guardar(refugio));
    }
}
