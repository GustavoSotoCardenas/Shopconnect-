package com.shopconnect.ms_productos.dto;

import java.math.BigDecimal;

public class ProductoDTO {

    private Long idProducto;
    private String nombre;
    private BigDecimal precioVenta;

    public ProductoDTO() {}

    public ProductoDTO(Long idProducto, String nombre, BigDecimal precioVenta) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.precioVenta = precioVenta;
    }

    public Long getIdProducto() { return idProducto; }
    public void setIdProducto(Long idProducto) { this.idProducto = idProducto; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public BigDecimal getPrecioVenta() { return precioVenta; }
    public void setPrecioVenta(BigDecimal precioVenta) { this.precioVenta = precioVenta; }
}
