package com.shopconnect.ms_usuarios.controller;

import com.shopconnect.ms_usuarios.dto.UsuarioDTO;
import com.shopconnect.ms_usuarios.model.Usuario;
import com.shopconnect.ms_usuarios.service.usuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuariosController {

    @Autowired
    private usuariosService usuariosService;

    @GetMapping
    public List<UsuarioDTO> obtenerUsuarios() {
        return usuariosService.listarTodos();
    }

    @PostMapping
    public UsuarioDTO crearUsuario(@RequestBody Usuario usuario) {
        return usuariosService.guardar(usuario);
    }
}
