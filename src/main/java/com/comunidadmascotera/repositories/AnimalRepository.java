package com.comunidadmascotera.repositories;

import com.comunidadmascotera.entities.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, String> {
    List<Animal> findBySpeciesIgnoreCase(String species);
    List<Animal> findByRefugioId(String refugioId);
    List<Animal> findByUrgentTrue();
    List<Animal> findByApartmentTrue();
}
