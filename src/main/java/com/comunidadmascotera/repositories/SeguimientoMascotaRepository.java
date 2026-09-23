package com.comunidadmascotera.repositories;

import com.comunidadmascotera.entities.SeguimientoMascota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SeguimientoMascotaRepository extends JpaRepository<SeguimientoMascota, String> {
    Optional<SeguimientoMascota> findByPetId(String petId);
    List<SeguimientoMascota> findByShelterId(String shelterId);
    List<SeguimientoMascota> findByHealthTrafficLight(String healthTrafficLight);
}
