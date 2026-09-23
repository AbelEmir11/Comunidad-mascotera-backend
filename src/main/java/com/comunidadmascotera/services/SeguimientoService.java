package com.comunidadmascotera.services;

import com.comunidadmascotera.entities.HitoSeguimiento;
import com.comunidadmascotera.entities.SeguimientoMascota;
import com.comunidadmascotera.repositories.HitoSeguimientoRepository;
import com.comunidadmascotera.repositories.SeguimientoMascotaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SeguimientoService {

    private final SeguimientoMascotaRepository seguimientoRepository;
    private final HitoSeguimientoRepository hitoRepository;

    public SeguimientoService(SeguimientoMascotaRepository seguimientoRepository, HitoSeguimientoRepository hitoRepository) {
        this.seguimientoRepository = seguimientoRepository;
        this.hitoRepository = hitoRepository;
    }

    @Transactional(readOnly = true)
    public List<SeguimientoMascota> listarTodos() {
        return seguimientoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<SeguimientoMascota> buscarPorId(String id) {
        return seguimientoRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public Optional<SeguimientoMascota> buscarPorMascota(String petId) {
        return seguimientoRepository.findByPetId(petId);
    }

    /**
     * Completa un hito de seguimiento: sube foto/anécdota, desbloquea la medalla
     * y actualiza el semáforo del refugio a "verde" (al día).
     */
    public HitoSeguimiento completarHito(String hitoId, String photoUrl, String notas) {
        HitoSeguimiento hito = hitoRepository.findById(hitoId)
                .orElseThrow(() -> new IllegalArgumentException("Hito no encontrado con ID: " + hitoId));

        hito.setStatus("completado");
        hito.setPhotoUrl(photoUrl);
        hito.setNotes(notas);
        hito.setCompletedAt("Completado recientemente");

        HitoSeguimiento guardado = hitoRepository.save(hito);

        // Actualizar semáforo del caso
        SeguimientoMascota seguimiento = hito.getSeguimiento();
        if (seguimiento != null) {
            seguimiento.setHealthTrafficLight("verde");
            seguimiento.setLastUpdateDate("Al día (reporte reciente)");
            seguimientoRepository.save(seguimiento);
        }

        return guardado;
    }
}
