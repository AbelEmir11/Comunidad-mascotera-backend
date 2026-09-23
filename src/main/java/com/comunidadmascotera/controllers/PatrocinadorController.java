package com.comunidadmascotera.controllers;

import com.comunidadmascotera.entities.Patrocinador;
import com.comunidadmascotera.repositories.PatrocinadorRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patrocinadores")
public class PatrocinadorController {

    private final PatrocinadorRepository patrocinadorRepository;

    public PatrocinadorController(PatrocinadorRepository patrocinadorRepository) {
        this.patrocinadorRepository = patrocinadorRepository;
    }

    @GetMapping
    public List<Patrocinador> listar(@RequestParam(required = false) String categoria) {
        if (categoria != null && !categoria.isBlank()) {
            return patrocinadorRepository.findByCategory(categoria);
        }
        return patrocinadorRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patrocinador> obtenerPorId(@PathVariable String id) {
        return patrocinadorRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Patrocinador> crear(@RequestBody Patrocinador patrocinador) {
        return ResponseEntity.ok(patrocinadorRepository.save(patrocinador));
    }
}
