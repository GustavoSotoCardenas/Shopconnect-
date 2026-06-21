package com.shopconnect.ms_productos.repository;

import com.shopconnect.ms_productos.model.ImagenProducto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ImagenProductoRepository extends JpaRepository<ImagenProducto, Long> {

    default List<ImagenProducto> listarTodos()                  { return findAll(); }
    default Optional<ImagenProducto> buscarPorId(Long id)       { return findById(id); }
    default ImagenProducto guardar(ImagenProducto imagen)        { return save(imagen); }
    default boolean existePorId(Long id)                        { return existsById(id); }
    default void eliminarPorId(Long id)                         { deleteById(id); }

    List<ImagenProducto> findByProducto_Id(Long idProducto);
    Optional<ImagenProducto> findByProducto_IdAndPrincipalTrue(Long idProducto);
}