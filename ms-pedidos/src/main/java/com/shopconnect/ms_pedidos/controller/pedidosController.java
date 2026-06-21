package com.shopconnect.ms_pedidos.controller;

import com.shopconnect.ms_pedidos.dto.PedidoDTO;
import com.shopconnect.ms_pedidos.model.Pedido;
import com.shopconnect.ms_pedidos.service.pedidosService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class pedidosController {

    @Autowired
    private pedidosService pedidosServ;

    @GetMapping
    public ResponseEntity<List<PedidoDTO>> listarTodos() {
        return ResponseEntity.ok(pedidosServ.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoDTO> obtenerPorId(@PathVariable Long id) {
        return pedidosServ.obtenerPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<PedidoDTO>> obtenerPorUsuario(@PathVariable Long usuarioId) {
        List<PedidoDTO> pedidos = pedidosServ.obtenerPorUsuario(usuarioId);
        if (pedidos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(pedidos);
    }

    @GetMapping("/estado/{estadoId}")
    public ResponseEntity<List<PedidoDTO>> obtenerPorEstado(@PathVariable Long estadoId) {
        List<PedidoDTO> pedidos = pedidosServ.obtenerPorEstado(estadoId);
        if (pedidos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(pedidos);
    }

    @PostMapping
    public ResponseEntity<PedidoDTO> crear(@Valid @RequestBody Pedido pedido) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidosServ.crear(pedido));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoDTO> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody Pedido pedido) {
        return pedidosServ.actualizar(id, pedido)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (pedidosServ.eliminar(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
