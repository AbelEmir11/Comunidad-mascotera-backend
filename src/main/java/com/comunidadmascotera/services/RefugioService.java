package com.comunidadmascotera.services;

import com.comunidadmascotera.entities.Refugio;
import com.comunidadmascotera.repositories.RefugioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class RefugioService {

    private final RefugioRepository refugioRepository;

    public RefugioService(RefugioRepository refugioRepository) {
        this.refugioRepository = refugioRepository;
    }

    public List<Refugio> listarTodos() {
        return refugioRepository.findAll();
    }

    public Optional<Refugio> buscarPorId(String id) {
        return refugioRepository.findById(id);
    }

    public List<Refugio> buscarPorZona(String zona) {
        if (zona == null || zona.equalsIgnoreCase("todas")) {
            return refugioRepository.findAll();
        }
        return refugioRepository.findByZoneIgnoreCase(zona);
    }

    @Transactional
    public Refugio guardar(Refugio refugio) {
        return refugioRepository.save(refugio);
    }
}
