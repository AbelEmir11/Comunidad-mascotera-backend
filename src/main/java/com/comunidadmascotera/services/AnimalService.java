package com.comunidadmascotera.services;

import com.comunidadmascotera.entities.Animal;
import com.comunidadmascotera.repositories.AnimalRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class AnimalService {

    private final AnimalRepository animalRepository;

    public AnimalService(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    public List<Animal> listarTodos() {
        return animalRepository.findAll();
    }

    public Optional<Animal> buscarPorId(String id) {
        return animalRepository.findById(id);
    }

    public List<Animal> buscarPorEspecie(String especie) {
        if (especie == null || especie.equalsIgnoreCase("todos")) {
            return animalRepository.findAll();
        }
        return animalRepository.findBySpeciesIgnoreCase(especie);
    }

    public List<Animal> buscarPorRefugio(String refugioId) {
        return animalRepository.findByRefugioId(refugioId);
    }

    public List<Animal> listarUrgentes() {
        return animalRepository.findByUrgentTrue();
    }

    @Transactional
    public Animal guardar(Animal animal) {
        return animalRepository.save(animal);
    }

    /**
     * Algoritmo de compatibilidad ("Match") implementado en capa de servicio de Java.
     * Retorna un puntaje de 0 a 100 evaluando vivienda, horas en soledad, convivencia y energía.
     */
    public int calcularAfinidad(Animal animal, String housing, String hoursAlone, 
                                 boolean hasKids, boolean hasCats, boolean hasDogs, String activityLevel) {
        int score = 50;

        // Evaluación de Vivienda
        if ("depto".equalsIgnoreCase(housing)) {
            if (animal.isApartment()) score += 20;
            else score -= 30;
        } else {
            score += 15;
        }

        // Evaluación de tiempo en soledad
        if ("muchas".equalsIgnoreCase(hoursAlone)) {
            if ("muchas".equalsIgnoreCase(animal.getHoursAloneOk())) score += 15;
            else if ("pocas".equalsIgnoreCase(animal.getHoursAloneOk())) score -= 20;
        } else {
            score += 10;
        }

        // Convivencia
        if (hasKids) {
            if (animal.isKids()) score += 15;
            else score -= 25;
        }
        if (hasCats) {
            if (animal.isCats()) score += 15;
            else score -= 30;
        }
        if (hasDogs) {
            if (animal.isOtherDogs()) score += 15;
            else score -= 25;
        }

        // Nivel de Energía
        if (animal.getEnergyLevel() != null && animal.getEnergyLevel().equalsIgnoreCase(activityLevel)) {
            score += 20;
        }

        return Math.min(99, Math.max(45, score));
    }
}
