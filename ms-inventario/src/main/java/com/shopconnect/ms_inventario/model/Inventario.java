package com.shopconnect.ms_inventario.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "inventario")
public class Inventario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idInventario;

    @NotNull(message = "El stock actual del producto es obligatorio")
    @Min(0)
    @Column(nullable = false)
    private Integer stockActual;

    @NotNull(message = "El stock minimo del producto es obligatorio")
    @Min(0)
    @Column(nullable = false)
    private Integer stockMinimo;

    public Inventario(){}

    public Inventario(Long idInventario, int stockActual, int stockMinimo){
        this.idInventario = idInventario;
        this.stockActual = stockActual;
        this.stockMinimo = stockMinimo;
    }

    public Long getIdInventario() { return idInventario; }
    public void setIdInventario(Long idInventario) { this.idInventario = idInventario; }

    public Integer getStockActual() { return stockActual; }
    public void setStockActual(Integer stockActual) { this.stockActual = stockActual; }

    public Integer getStockMinimo() { return stockMinimo; }
    public void setStockMinimo(Integer stockMinimo) { this.stockMinimo = stockMinimo; }

    @Override
    public String toString(){
        return "Inventario{id=" + idInventario +
        ", stockActual=" + stockActual +
        ", stockMinimo=" + stockMinimo + "}";
    }
}

