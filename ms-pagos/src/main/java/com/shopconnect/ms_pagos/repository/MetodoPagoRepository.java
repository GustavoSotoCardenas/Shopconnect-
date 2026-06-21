package com.shopconnect.ms_pagos.repository;

import com.shopconnect.ms_pagos.model.MetodoPago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MetodoPagoRepository extends JpaRepository<MetodoPago, Long> {

    default List<MetodoPago> listarTodos()                      { return findAll(); }
    default Optional<MetodoPago> buscarPorId(Long id)           { return findById(id); }
    default MetodoPago guardar(MetodoPago metodoPago)            { return save(metodoPago); }
    default boolean existePorId(Long id)                        { return existsById(id); }
    default void eliminarPorId(Long id)                         { deleteById(id); }

    List<MetodoPago> findByActivoTrue();
    Optional<MetodoPago> findByNombre(String nombre);
    boolean existsByNombre(String nombre);
}
