package com.shopconnect.ms_inventario.dto;

public class InventarioDTO {

    private Long idInventario;
    private Integer stockActual;

    public InventarioDTO() {}

    public InventarioDTO(Long idInventario, Integer stockActual) {
        this.idInventario = idInventario;
        this.stockActual = stockActual;
    }

    public Long getIdInventario() { return idInventario; }
    public void setIdInventario(Long idInventario) { this.idInventario = idInventario; }

    public Integer getStockActual() { return stockActual; }
    public void setStockActual(Integer stockActual) { this.stockActual = stockActual; }
}
