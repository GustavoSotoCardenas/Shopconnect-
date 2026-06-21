package com.shopconnect.ms_productos.repository;

import com.shopconnect.ms_productos.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    default List<Categoria> listarTodos()                       { return findAll(); }
    default Optional<Categoria> buscarPorId(Long id)            { return findById(id); }
    default Categoria guardar(Categoria categoria)               { return save(categoria); }
    default boolean existePorId(Long id)                        { return existsById(id); }
    default void eliminarPorId(Long id)                         { deleteById(id); }

    Optional<Categoria> findByNombre(String nombre);
    List<Categoria> findByActivaTrue();
    boolean existsByNombre(String nombre);
}