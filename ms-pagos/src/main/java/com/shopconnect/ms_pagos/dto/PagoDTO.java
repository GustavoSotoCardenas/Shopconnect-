package com.shopconnect.ms_pagos.dto;

import java.math.BigDecimal;

public class PagoDTO {

    private Long idPedido;
    private BigDecimal monto;

    public PagoDTO() {}

    public PagoDTO(Long idPedido, BigDecimal monto) {
        this.idPedido = idPedido;
        this.monto = monto;
    }

    public Long getIdPedido() { return idPedido; }
    public void setIdPedido(Long idPedido) { this.idPedido = idPedido; }

    public BigDecimal getMonto() { return monto; }
    public void setMonto(BigDecimal monto) { this.monto = monto; }
}
