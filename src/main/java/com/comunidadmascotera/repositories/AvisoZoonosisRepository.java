package com.comunidadmascotera.repositories;

import com.comunidadmascotera.entities.AvisoZoonosis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AvisoZoonosisRepository extends JpaRepository<AvisoZoonosis, String> {
    List<AvisoZoonosis> findByMunicipalityIgnoreCase(String municipality);
    List<AvisoZoonosis> findByType(String type);
}
