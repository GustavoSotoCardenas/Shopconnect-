package com.shopconnect.ms_pagos.service;

import com.shopconnect.ms_pagos.dto.PagoDTO;
import com.shopconnect.ms_pagos.model.MetodoPago;
import com.shopconnect.ms_pagos.model.Pago;
import com.shopconnect.ms_pagos.repository.MetodoPagoRepository;
import com.shopconnect.ms_pagos.repository.PagoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PagoService {

    private static final Logger log = LoggerFactory.getLogger(PagoService.class);

    @Autowired
    private PagoRepository pagoRepository;

    @Autowired
    private MetodoPagoRepository metodoPagoRepository;

    // ── Mapeo entidad → DTO ──────────────────────────────────────────────────
    public PagoDTO toDTO(Pago pago) {
        return new PagoDTO(
            pago.getPedidoId(),
            pago.getMonto()
        );
    }

    // ── CRUD Pago ────────────────────────────────────────────────────────────

    public List<PagoDTO> listarTodos() {
        log.info("[PagoService] Listando todos los pagos");
        return pagoRepository.listarTodos()
            .stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
    }

    public Optional<PagoDTO> buscarPorId(Long id) {
        log.info("[PagoService] Buscando pago id={}", id);
        return pagoRepository.buscarPorId(id).map(this::toDTO);
    }

    public Optional<PagoDTO> buscarPorPedido(Long pedidoId) {
        log.info("[PagoService] Buscando pago por pedidoId={}", pedidoId);
        return pagoRepository.findByPedidoId(pedidoId).map(this::toDTO);
    }

    public List<PagoDTO> listarPorMetodoPago(Long idMetodoPago) {
        log.info("[PagoService] Listando pagos por metodoPago id={}", idMetodoPago);
        return pagoRepository.findByMetodoPago_IdMetodoPago(idMetodoPago)
            .stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
    }

    public List<PagoDTO> listarPorMetodoYMontoMinimo(Long idMetodo, BigDecimal monto) {
        log.info("[PagoService] Filtrando pagos metodo={} montoMin={}", idMetodo, monto);
        return pagoRepository.findByMetodoAndMontoMinimo(idMetodo, monto)
            .stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
    }

    @Transactional
    public PagoDTO crear(Pago pago, Long idMetodoPago) {
        log.info("[PagoService] Creando pago para pedidoId={}", pago.getPedidoId());

        if (pagoRepository.findByPedidoId(pago.getPedidoId()).isPresent()) {
            log.warn("[PagoService] Ya existe un pago para pedidoId={}", pago.getPedidoId());
            throw new IllegalArgumentException(
                "Ya existe un pago registrado para el pedido: " + pago.getPedidoId()
            );
        }

        MetodoPago metodoPago = metodoPagoRepository.buscarPorId(idMetodoPago)
            .orElseThrow(() -> new RuntimeException(
                "Método de pago no encontrado con id: " + idMetodoPago
            ));

        if (!metodoPago.getActivo()) {
            throw new IllegalArgumentException(
                "El método de pago no está activo: " + metodoPago.getNombre()
            );
        }

        if (pago.getMonto().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto del pago debe ser mayor a 0");
        }

        pago.setMetodoPago(metodoPago);
        Pago guardado = pagoRepository.guardar(pago);
        log.info("[PagoService] Pago creado con id={}", guardado.getId());
        return toDTO(guardado);
    }

    @Transactional
    public PagoDTO actualizar(Long id, Pago datos) {
        Pago pago = pagoRepository.buscarPorId(id)
            .orElseThrow(() -> new RuntimeException("Pago no encontrado: " + id));

        if (datos.getMonto() != null) pago.setMonto(datos.getMonto());

        log.info("[PagoService] Pago actualizado id={}", id);
        return toDTO(pagoRepository.guardar(pago));
    }

    @Transactional
    public void eliminar(Long id) {
        if (!pagoRepository.existePorId(id)) {
            throw new RuntimeException("Pago no encontrado con id: " + id);
        }
        pagoRepository.eliminarPorId(id);
        log.info("[PagoService] Pago eliminado id={}", id);
    }

    // ── CRUD MetodoPago ──────────────────────────────────────────────────────

    public List<MetodoPago> listarMetodos() {
        return metodoPagoRepository.listarTodos();
    }

    public List<MetodoPago> listarMetodosActivos() {
        return metodoPagoRepository.findByActivoTrue();
    }

    public Optional<MetodoPago> buscarMetodoPorId(Long id) {
        return metodoPagoRepository.buscarPorId(id);
    }

    @Transactional
    public MetodoPago crearMetodo(MetodoPago metodoPago) {
        if (metodoPagoRepository.existsByNombre(metodoPago.getNombre())) {
            throw new IllegalArgumentException(
                "Ya existe un método de pago con el nombre: " + metodoPago.getNombre()
            );
        }
        MetodoPago guardado = metodoPagoRepository.guardar(metodoPago);
        log.info("[PagoService] MetodoPago creado: {}", guardado.getNombre());
        return guardado;
    }

    @Transactional
    public MetodoPago actualizarMetodo(Long id, MetodoPago datos) {
        MetodoPago metodo = metodoPagoRepository.buscarPorId(id)
            .orElseThrow(() -> new RuntimeException("Método de pago no encontrado: " + id));

        if (datos.getNombre() != null) metodo.setNombre(datos.getNombre());
        if (datos.getActivo() != null) metodo.setActivo(datos.getActivo());

        return metodoPagoRepository.guardar(metodo);
    }

    @Transactional
    public void eliminarMetodo(Long id) {
        if (!metodoPagoRepository.existePorId(id)) {
            throw new RuntimeException("Método de pago no encontrado: " + id);
        }
        metodoPagoRepository.eliminarPorId(id);
        log.info("[PagoService] MetodoPago eliminado id={}", id);
    }
}
