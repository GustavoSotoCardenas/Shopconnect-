package com.shopconnect.ms_productos.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "categoria")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre de la categoria es obligatorio")
    @Size(min = 1, max = 50, message = "El nombre debe tener entre 1 y 50 caracteres")
    @Column(nullable = false, length = 50)
    private String nombre;

    @NotBlank(message = "La descripcion es obligatoria")
    @Size(min = 1, max = 200, message = "La descripcion debe tener entre 1 y 200 caracteres")
    @Column(nullable = false, length = 200)
    private String descripcion;

    @Column(nullable = false)
    private Boolean activa = true;

    //Getters and setters
    public Categoria(){}

    public Categoria(String nombre, String descripcion, Boolean activa){
    this.nombre = nombre;
    this.descripcion= descripcion;
    this.activa = activa;
    }


    public Long getId() { return id; }
    public void setId(Long id_categoria) { this.id = id_categoria; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public Boolean getActiva() { return activa; }
    public void setActiva(Boolean activa) { this.activa = activa; }

    @Override
    public String toString(){
        return "Categoria{id=" + id + 
        ",nombre='" + nombre + 
        "', descripcion='" + descripcion + 
        "', activa=" + activa + "}";
    }
}
