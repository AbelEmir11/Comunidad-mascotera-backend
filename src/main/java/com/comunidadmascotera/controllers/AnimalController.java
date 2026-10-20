package com.comunidadmascotera.controllers;

import com.comunidadmascotera.dtos.AnimalResponseDto;
import com.comunidadmascotera.entities.Animal;
import com.comunidadmascotera.services.AnimalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/animales")
public class AnimalController {

    private final AnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @GetMapping
    public List<AnimalResponseDto> listar(
            @RequestParam(required = false) String especie,
            @RequestParam(required = false) String refugioId,
            @RequestParam(required = false) Boolean urgente) {

        List<Animal> animales;
        if (Boolean.TRUE.equals(urgente)) {
            animales = animalService.listarUrgentes();
        } else if (refugioId != null && !refugioId.isBlank()) {
            animales = animalService.buscarPorRefugio(refugioId);
        } else if (especie != null && !especie.isBlank()) {
            animales = animalService.buscarPorEspecie(especie);
        } else {
            animales = animalService.listarTodos();
        }

        return animales.stream()
                .map(AnimalResponseDto::fromEntity)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnimalResponseDto> obtenerPorId(@PathVariable String id) {
        return animalService.buscarPorId(id)
                .map(AnimalResponseDto::fromEntity)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<AnimalResponseDto> crear(@RequestBody Animal animal) {
        Animal guardado = animalService.guardar(animal);
        return ResponseEntity.ok(AnimalResponseDto.fromEntity(guardado));
    }

    /**
     * Endpoint para calcular el "Match" de compatibilidad directamente en el servidor Java.
     */
    @PostMapping("/test-match")
    public List<Map<String, Object>> calcularMatch(@RequestBody Map<String, Object> respuestas) {
        String housing = (String) respuestas.getOrDefault("housing", "casa_patio");
        String hoursAlone = (String) respuestas.getOrDefault("hoursAlone", "medias");
        String activityLevel = (String) respuestas.getOrDefault("activityLevel", "medio");
        String speciesPref = (String) respuestas.getOrDefault("speciesPref", "todos");

        @SuppressWarnings("unchecked")
        Map<String, Boolean> coexistence = (Map<String, Boolean>) respuestas.getOrDefault("coexistence", Collections.emptyMap());
        boolean hasKids = Boolean.TRUE.equals(coexistence.get("kids"));
        boolean hasCats = Boolean.TRUE.equals(coexistence.get("cats"));
        boolean hasDogs = Boolean.TRUE.equals(coexistence.get("dogs"));

        List<Animal> todos = animalService.listarTodos();
        List<Map<String, Object>> resultados = new ArrayList<>();

        for (Animal a : todos) {
            if (!"todos".equalsIgnoreCase(speciesPref) && !a.getSpecies().equalsIgnoreCase(speciesPref)) {
                continue;
            }

            int score = animalService.calcularAfinidad(a, housing, hoursAlone, hasKids, hasCats, hasDogs, activityLevel);

            Map<String, Object> item = new HashMap<>();
            item.put("animal", AnimalResponseDto.fromEntity(a));
            item.put("matchScore", score);
            resultados.add(item);
        }

        resultados.sort((r1, r2) -> Integer.compare((int) r2.get("matchScore"), (int) r1.get("matchScore")));
        return resultados;
    }
}
