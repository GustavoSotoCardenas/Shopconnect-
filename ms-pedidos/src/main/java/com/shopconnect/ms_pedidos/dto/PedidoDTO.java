package com.shopconnect.ms_pedidos.dto;

import java.math.BigDecimal;

public class PedidoDTO {

    private Long idPedido;
    private BigDecimal total;
    private String estado;

    public PedidoDTO() {}

    public PedidoDTO(Long idPedido, BigDecimal total, String estado) {
        this.idPedido = idPedido;
        this.total = total;
        this.estado = estado;
    }

    public Long getIdPedido() { return idPedido; }
    public void setIdPedido(Long idPedido) { this.idPedido = idPedido; }

    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
