package com.shopconnect.ms_usuarios.dto;

public class UsuarioDTO {

    private Long idUsuario;
    private String nombre;
    private String correoElectronico;

    public UsuarioDTO() {}

    public UsuarioDTO(Long idUsuario, String nombre, String correoElectronico) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.correoElectronico = correoElectronico;
    }

    public Long getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Long idUsuario) { this.idUsuario = idUsuario; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCorreoElectronico() { return correoElectronico; }
    public void setCorreoElectronico(String correoElectronico) { this.correoElectronico = correoElectronico; }
}
