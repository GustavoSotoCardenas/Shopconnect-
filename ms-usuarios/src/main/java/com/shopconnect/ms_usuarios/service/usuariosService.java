package com.shopconnect.ms_usuarios.service;

import com.shopconnect.ms_usuarios.dto.UsuarioDTO;
import com.shopconnect.ms_usuarios.model.Usuario;
import com.shopconnect.ms_usuarios.repository.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class usuariosService {

    @Autowired
    private UsuariosRepository usuariosRepository;

    // ── Mapeo entidad → DTO ──────────────────────────────────────────────────
    public UsuarioDTO toDTO(Usuario usuario) {
        return new UsuarioDTO(
            usuario.getIdUsuario(),
            usuario.getNombre(),
            usuario.getEmail()          // email del modelo → correoElectronico en el DTO
        );
    }

    // ── Métodos de negocio ───────────────────────────────────────────────────

    public List<UsuarioDTO> listarTodos() {
        return usuariosRepository.findAll()
            .stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
    }

    public UsuarioDTO guardar(Usuario usuario) {
        return toDTO(usuariosRepository.save(usuario));
    }

    public Optional<UsuarioDTO> buscarPorId(Long id) {
        return usuariosRepository.findById(id).map(this::toDTO);
    }

    public void eliminar(Long id) {
        usuariosRepository.deleteById(id);
    }
}
