package com.shopconnect.ms_usuarios.service;

import com.shopconnect.ms_usuarios.model.Direccion;
import com.shopconnect.ms_usuarios.repository.DireccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DireccionService {

    @Autowired
    private DireccionRepository direccionRepository;

    public List<Direccion> listarTodas() {
        return direccionRepository.findAll();
    }

    public Direccion guardar(Direccion direccion) {
        return direccionRepository.save(direccion);
    }
}