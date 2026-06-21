package com.shopconnect.ms_usuarios.repository;

import com.shopconnect.ms_usuarios.model.RolUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolUsuarioRepository extends JpaRepository<RolUsuario, Long> {
}