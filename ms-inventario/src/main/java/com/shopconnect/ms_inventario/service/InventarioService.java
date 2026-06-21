package com.shopconnect.ms_inventario.service;

import com.shopconnect.ms_inventario.dto.InventarioDTO;
import com.shopconnect.ms_inventario.model.Inventario;
import com.shopconnect.ms_inventario.repository.InventarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventarioService {

    @Autowired
    private InventarioRepository inventarioRepository;

    // ── Mapeo entidad → DTO ──────────────────────────────────────────────────
    public InventarioDTO toDTO(Inventario inventario) {
        return new InventarioDTO(
            inventario.getIdInventario(),
            inventario.getStockActual()
        );
    }

    // ── Métodos de negocio ───────────────────────────────────────────────────

    public List<InventarioDTO> listarTodo() {
        return inventarioRepository.findAll()
            .stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
    }

    public InventarioDTO guardar(Inventario inventario) {
        return toDTO(inventarioRepository.save(inventario));
    }
}
