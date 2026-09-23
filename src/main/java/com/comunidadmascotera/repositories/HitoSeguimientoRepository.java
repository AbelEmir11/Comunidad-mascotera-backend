package com.comunidadmascotera.repositories;

import com.comunidadmascotera.entities.HitoSeguimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HitoSeguimientoRepository extends JpaRepository<HitoSeguimiento, String> {
    List<HitoSeguimiento> findBySeguimientoId(String seguimientoId);
}
