package com.shopconnect.ms_inventario.controller;

import com.shopconnect.ms_inventario.dto.InventarioDTO;
import com.shopconnect.ms_inventario.model.Inventario;
import com.shopconnect.ms_inventario.service.InventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventario")
public class InventarioController {

    @Autowired
    private InventarioService inventarioService;

    @GetMapping
    public List<InventarioDTO> obtenerInventario() {
        return inventarioService.listarTodo();
    }

    @PostMapping
    public InventarioDTO crearInventario(@RequestBody Inventario inventario) {
        return inventarioService.guardar(inventario);
    }
}
