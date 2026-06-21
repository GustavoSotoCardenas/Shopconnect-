package com.shopconnect.ms_inventario.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "movimiento_inventario")
public class MovimientoInv {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMovimientoInventario;

    @NotBlank(message = "El tipo de movimiento es obligatorio")
    @Size(max = 50)
    @Column(nullable = false, length = 50)
    private String tipo;

    @NotNull(message = "La cantidad minima es obligatoria")
    @DecimalMin(value = "0", inclusive = false, message = "Debe contener número positivo")
    @Column(nullable = false)
    private Integer cantidad;

    public MovimientoInv() {
    }

    public MovimientoInv(String tipo, Integer cantidad) {
        this.tipo = tipo;
        this.cantidad = cantidad;
    }

    // Getters and Setters
    public Long getId() { return idMovimientoInventario; }
    public void setId(Long idMovimientoInventario) { this.idMovimientoInventario = idMovimientoInventario; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public Integer getCantidad() { return cantidad; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }

    @Override
    public String toString(){
        return "MovimientoInventario{id=" + idMovimientoInventario +
        "tipo='" + tipo +
        "', cantidad=" +cantidad + "}";
    }
}