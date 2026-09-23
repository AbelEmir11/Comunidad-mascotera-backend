package com.comunidadmascotera.repositories;

import com.comunidadmascotera.entities.SolicitudAdopcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SolicitudAdopcionRepository extends JpaRepository<SolicitudAdopcion, Long> {
    List<SolicitudAdopcion> findByAnimalId(String animalId);
    List<SolicitudAdopcion> findByStatus(String status);
}
