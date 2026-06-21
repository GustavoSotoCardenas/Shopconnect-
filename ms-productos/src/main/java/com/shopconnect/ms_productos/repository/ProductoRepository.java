package com.shopconnect.ms_productos.repository;

import com.shopconnect.ms_productos.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    default List<Producto> listarTodos(){ return findAll(); }
    default Optional<Producto> buscarPorId(Long id){ return findById(id); }
    default Producto guardar(Producto producto){ return save(producto); }
    default boolean existePorId(Long id){ return existsById(id); }
    default void eliminarPorId(Long id){ deleteById(id); }

    Optional<Producto> findBySku(String sku);
    List<Producto> findByNombreContainingIgnoreCase(String nombre);
    List<Producto> findByCategoria_Id(Long idCategoria);
    List<Producto> findByMarca_Id(Long idMarca);
    boolean existsBySku(String sku);

    @Query("SELECT p FROM Producto p WHERE p.categoria.id = :idCategoria AND p.marca.id = :idMarca")
    List<Producto> findByCategoriaAndMarca(
            @Param("idCategoria") Long idCategoria,
            @Param("idMarca") Long idMarca
    );
}

