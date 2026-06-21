package com.shopconnect.ms_pagos.repository;

import com.shopconnect.ms_pagos.model.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface PagoRepository extends JpaRepository<Pago, Long> {

    default List<Pago> listarTodos()                            { return findAll(); }
    default Optional<Pago> buscarPorId(Long id)                 { return findById(id); }
    default Pago guardar(Pago pago)                             { return save(pago); }
    default boolean existePorId(Long id)                        { return existsById(id); }
    default void eliminarPorId(Long id)                         { deleteById(id); }

    Optional<Pago> findByPedidoId(Long pedidoId);
    List<Pago> findByMetodoPago_IdMetodoPago(Long idMetodoPago);

    @Query("SELECT p FROM Pago p WHERE p.metodoPago.idMetodoPago = :idMetodo AND p.monto >= :monto")
    List<Pago> findByMetodoAndMontoMinimo(
            @Param("idMetodo") Long idMetodo,
            @Param("monto") BigDecimal monto
    );
}