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
@Table(name = "marca")
public class Marca {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotBlank(message = "El nombre de la marca es obligatorio")
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
    @Column(nullable = false, length = 100)
    private String nombre;

    @NotBlank(message = "El nombre del pais de origen es obligatorio")
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
    @Column(nullable = false, length = 100)
    private String paisOrigen;

    @Size(max = 200)
    @Column(length = 200)
    private String logoUrl;


    //Getters and setters
    public Marca(){}

    public Marca(String nombre, String paisOrigen, String logoUrl){
        this.nombre = nombre;
        this.paisOrigen = paisOrigen;
        this.logoUrl = logoUrl;
    }

    public Long getId() { return id; }
    public void setId(Long id_marca) { this.id = id_marca; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getPaisOrigen() { return paisOrigen; }
    public void setPaisOrigen(String paisOrigen) { this.paisOrigen = paisOrigen; }

    public String getLogoUrl() { return logoUrl; }
    public void setLogoUrl(String logoUrl) { this.logoUrl = logoUrl; }

    @Override
    public String toString(){
        return "Marca{id=" + id +
        ",nombre='" + nombre +
        "', paisOrigen='" + paisOrigen +
        "', LogoUrl='" + logoUrl + "'}";
    }

}
