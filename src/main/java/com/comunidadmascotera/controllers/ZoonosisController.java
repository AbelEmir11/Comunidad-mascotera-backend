package com.comunidadmascotera.controllers;

import com.comunidadmascotera.entities.AvisoZoonosis;
import com.comunidadmascotera.repositories.AvisoZoonosisRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/zoonosis")
public class ZoonosisController {

    private final AvisoZoonosisRepository avisoRepository;

    public ZoonosisController(AvisoZoonosisRepository avisoRepository) {
        this.avisoRepository = avisoRepository;
    }

    @GetMapping
    public List<AvisoZoonosis> listar(@RequestParam(required = false) String municipio) {
        if (municipio != null && !municipio.isBlank() && !municipio.equalsIgnoreCase("todos")) {
            return avisoRepository.findByMunicipalityIgnoreCase(municipio);
        }
        return avisoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AvisoZoonosis> obtenerPorId(@PathVariable String id) {
        return avisoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<AvisoZoonosis> crear(@RequestBody AvisoZoonosis aviso) {
        return ResponseEntity.ok(avisoRepository.save(aviso));
    }
}
