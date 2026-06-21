package com.shopconnect.ms_pedidos.service;

import com.shopconnect.ms_pedidos.dto.PedidoDTO;
import com.shopconnect.ms_pedidos.model.Pedido;
import com.shopconnect.ms_pedidos.repository.pedidosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class pedidosService {

    @Autowired
    private pedidosRepository pedidosRepo;

    // ── Mapeo entidad → DTO ──────────────────────────────────────────────────
    public PedidoDTO toDTO(Pedido pedido) {
        return new PedidoDTO(
            pedido.getId(),
            pedido.getTotal(),
            pedido.getEstadoPedido() != null ? pedido.getEstadoPedido().getNombre() : null
        );
    }

    // ── Métodos de negocio ───────────────────────────────────────────────────

    public List<PedidoDTO> listarTodos() {
        return pedidosRepo.findAll()
            .stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
    }

    public Optional<PedidoDTO> obtenerPorId(Long id) {
        return pedidosRepo.findById(id).map(this::toDTO);
    }

    public List<PedidoDTO> obtenerPorUsuario(Long usuarioId) {
        return pedidosRepo.findByUsuarioId(usuarioId)
            .stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
    }

    public List<PedidoDTO> obtenerPorEstado(Long estadoId) {
        return pedidosRepo.findByEstadoPedidoId(estadoId)
            .stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
    }

    public PedidoDTO crear(Pedido pedido) {
        return toDTO(pedidosRepo.save(pedido));
    }

    public Optional<PedidoDTO> actualizar(Long id, Pedido pedidoActualizado) {
        return pedidosRepo.findById(id).map(pedidoExistente -> {
            pedidoExistente.setFechaPedido(pedidoActualizado.getFechaPedido());
            pedidoExistente.setTotal(pedidoActualizado.getTotal());
            pedidoExistente.setUsuarioId(pedidoActualizado.getUsuarioId());
            pedidoExistente.setEstadoPedido(pedidoActualizado.getEstadoPedido());
            return toDTO(pedidosRepo.save(pedidoExistente));
        });
    }

    public boolean eliminar(Long id) {
        if (pedidosRepo.existsById(id)) {
            pedidosRepo.deleteById(id);
            return true;
        }
        return false;
    }
}
