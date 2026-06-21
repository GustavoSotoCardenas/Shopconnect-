package com.shopconnect.ms_usuarios.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "rol_usuario")
public class RolUsuario {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long idRolUsuario;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100)
    @Column(nullable = false, length = 100)
    private String nombre;

    @Size(max = 500, message = "La descripción no puede superar 500 caracteres")
    @Column(length = 500)
    private String descripcion;

    public RolUsuario(){}

    public RolUsuario(String nombre, String descripcion){
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public Long getIdRolUsuario() {return idRolUsuario;}
    public void setIdRolUsuario(Long idRolUsuario) {this.idRolUsuario = idRolUsuario;}

    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}

    public String getDescripcion() {return descripcion;}
    public void setDescripcion(String descripcion) {this.descripcion = descripcion;}

    @Override
    public String toString(){
        return "RolUsuario{id=" + idRolUsuario +
        ", nombre='" + nombre + "'" +
        ", descripcion='" + descripcion + "'}";
    }
    
}
