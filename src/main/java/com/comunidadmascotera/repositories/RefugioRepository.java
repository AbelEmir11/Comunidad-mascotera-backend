package com.comunidadmascotera.repositories;

import com.comunidadmascotera.entities.Refugio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RefugioRepository extends JpaRepository<Refugio, String> {
    List<Refugio> findByZoneIgnoreCase(String zone);
}
