
package com.shopconnect.ms_pedidos.repository;

import com.shopconnect.ms_pedidos.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface pedidosRepository extends JpaRepository<Pedido, Long> {

    // Buscar todos los pedidos de un usuario
    List<Pedido> findByUsuarioId(Long usuarioId);

    // Buscar pedidos por estado
    List<Pedido> findByEstadoPedidoId(Long estadoId);
}
