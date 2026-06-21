package com.shopconnect.ms_pedidos.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.DecimalMin;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "La fecha del pedido es obligatoria")
    @Column(nullable = false)
    private LocalDateTime fechaPedido;

    @NotNull(message = "El total del pedido es obligatorio")
    @DecimalMin(value = "0.0", inclusive = false, message = "El total debe ser mayor a 0")
    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal total;

    // Referencia cruzada a ms-usuarios — no es relacion JPA
    @NotNull(message = "El usuario es obligatorio")
    @Column(nullable = false)
    private Long usuarioId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_estado_pedido", nullable = false)
    private EstadoPedido estadoPedido;

    public Pedido() {}

    public Pedido(LocalDateTime fechaPedido, BigDecimal total, Long usuarioId, EstadoPedido estadoPedido) {
        this.fechaPedido = fechaPedido;
        this.total = total;
        this.usuarioId = usuarioId;
        this.estadoPedido = estadoPedido;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDateTime getFechaPedido() { return fechaPedido; }
    public void setFechaPedido(LocalDateTime fechaPedido) { this.fechaPedido = fechaPedido; }

    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; }

    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }

    public EstadoPedido getEstadoPedido() { return estadoPedido; }
    public void setEstadoPedido(EstadoPedido estadoPedido) { this.estadoPedido = estadoPedido; }

    @Override
    public String toString() {
        return "Pedido{id=" + id +
               ", fechaPedido=" + fechaPedido +
               ", total=" + total +
               ", usuarioId=" + usuarioId + "}";
    }
}
