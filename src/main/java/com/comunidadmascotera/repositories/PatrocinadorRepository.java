package com.comunidadmascotera.repositories;

import com.comunidadmascotera.entities.Patrocinador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatrocinadorRepository extends JpaRepository<Patrocinador, String> {
    List<Patrocinador> findByCategory(String category);
}
