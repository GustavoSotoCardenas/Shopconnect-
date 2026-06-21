package com.shopconnect.ms_pagos.controller;

import com.shopconnect.ms_pagos.dto.PagoDTO;
import com.shopconnect.ms_pagos.model.MetodoPago;
import com.shopconnect.ms_pagos.model.Pago;
import com.shopconnect.ms_pagos.service.PagoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/pagos")
public class PagoController {

    @Autowired
    private PagoService pagoService;

    // ── ENDPOINTS Pago ───────────────────────────────────────────────────────

    @GetMapping
    public ResponseEntity<List<PagoDTO>> listarTodos() {
        return ResponseEntity.ok(pagoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        return pagoService.buscarPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/pedido/{pedidoId}")
    public ResponseEntity<?> buscarPorPedido(@PathVariable Long pedidoId) {
        return pagoService.buscarPorPedido(pedidoId)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/metodo/{idMetodoPago}")
    public ResponseEntity<List<PagoDTO>> listarPorMetodoPago(@PathVariable Long idMetodoPago) {
        return ResponseEntity.ok(pagoService.listarPorMetodoPago(idMetodoPago));
    }

    @GetMapping("/filtrar")
    public ResponseEntity<List<PagoDTO>> filtrar(
            @RequestParam Long idMetodo,
            @RequestParam BigDecimal monto) {
        return ResponseEntity.ok(pagoService.listarPorMetodoYMontoMinimo(idMetodo, monto));
    }

    @PostMapping
    public ResponseEntity<?> crear(
            @Valid @RequestBody Pago pago,
            @RequestParam Long idMetodoPago) {
        try {
            PagoDTO creado = pagoService.crear(pago, idMetodoPago);
            return ResponseEntity.status(HttpStatus.CREATED).body(creado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Map.of("error", e.getMessage()));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                .body(Map.of("error", e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(
            @PathVariable Long id,
            @RequestBody Pago datos) {
        try {
            return ResponseEntity.ok(pagoService.actualizar(id, datos));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            pagoService.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // ── ENDPOINTS MetodoPago ─────────────────────────────────────────────────

    @GetMapping("/metodos")
    public ResponseEntity<List<MetodoPago>> listarMetodos() {
        return ResponseEntity.ok(pagoService.listarMetodos());
    }

    @GetMapping("/metodos/activos")
    public ResponseEntity<List<MetodoPago>> listarMetodosActivos() {
        return ResponseEntity.ok(pagoService.listarMetodosActivos());
    }

    @GetMapping("/metodos/{id}")
    public ResponseEntity<?> buscarMetodoPorId(@PathVariable Long id) {
        return pagoService.buscarMetodoPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/metodos")
    public ResponseEntity<?> crearMetodo(@Valid @RequestBody MetodoPago metodoPago) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                .body(pagoService.crearMetodo(metodoPago));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Map.of("error", e.getMessage()));
        }
    }

    @PutMapping("/metodos/{id}")
    public ResponseEntity<?> actualizarMetodo(
            @PathVariable Long id,
            @RequestBody MetodoPago datos) {
        try {
            return ResponseEntity.ok(pagoService.actualizarMetodo(id, datos));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/metodos/{id}")
    public ResponseEntity<?> eliminarMetodo(@PathVariable Long id) {
        try {
            pagoService.eliminarMetodo(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
