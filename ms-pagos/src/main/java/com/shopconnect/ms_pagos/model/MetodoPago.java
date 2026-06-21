package com.shopconnect.ms_pagos.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name= "metodo_pago")
public class MetodoPago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMetodoPago;

    @NotBlank(message = "El nombre del metodo de pago es obligatorio")
    @Column(nullable = false, length = 150)
    private String nombre;

    @Column(nullable = false)
    private Boolean activo;

    public MetodoPago(){}

    public Long getIdMetodoPago() {return idMetodoPago;}
    public void setIdMetodoPago(Long idMetodoPago){this.idMetodoPago = idMetodoPago;}
    
    public String getNombre() {return nombre;}
    public void setNombre(String nombre){this.nombre = nombre;}
    
    public Boolean getActivo() {return activo;}
    public void setActivo(Boolean activo){this.activo = activo;}

}
