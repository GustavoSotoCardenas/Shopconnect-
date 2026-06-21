package com.shopconnect.ms_productos.repository;

import com.shopconnect.ms_productos.model.Marca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, Long> {

    default List<Marca> listarTodos()                           { return findAll(); }
    default Optional<Marca> buscarPorId(Long id)                { return findById(id); }
    default Marca guardar(Marca marca)                          { return save(marca); }
    default boolean existePorId(Long id)                        { return existsById(id); }
    default void eliminarPorId(Long id)                         { deleteById(id); }

    Optional<Marca> findByNombre(String nombre);
    List<Marca> findByPaisOrigen(String paisOrigen);
    boolean existsByNombre(String nombre);
}