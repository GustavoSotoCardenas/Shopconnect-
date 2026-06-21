package com.shopconnect.ms_productos.service;

import com.shopconnect.ms_productos.dto.ProductoDTO;
import com.shopconnect.ms_productos.model.Categoria;
import com.shopconnect.ms_productos.model.ImagenProducto;
import com.shopconnect.ms_productos.model.Marca;
import com.shopconnect.ms_productos.model.Producto;
import com.shopconnect.ms_productos.repository.CategoriaRepository;
import com.shopconnect.ms_productos.repository.ImagenProductoRepository;
import com.shopconnect.ms_productos.repository.MarcaRepository;
import com.shopconnect.ms_productos.repository.ProductoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductoService {

    private static final Logger log = LoggerFactory.getLogger(ProductoService.class);

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private MarcaRepository marcaRepository;

    @Autowired
    private ImagenProductoRepository imagenProductoRepository;

    // ── Mapeo entidad → DTO ──────────────────────────────────────────────────
    public ProductoDTO toDTO(Producto producto) {
        return new ProductoDTO(
            producto.getId(),
            producto.getNombre(),
            producto.getPrecio()
        );
    }

    // ── CRUD Producto ────────────────────────────────────────────────────────

    public List<ProductoDTO> listarTodos() {
        log.info("[ProductoService] Listando todos los productos");
        return productoRepository.listarTodos()
            .stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
    }

    public Optional<ProductoDTO> buscarPorId(Long id) {
        log.info("[ProductoService] Buscando producto id={}", id);
        return productoRepository.buscarPorId(id).map(this::toDTO);
    }

    public Optional<Producto> buscarPorSku(String sku) {
        log.info("[ProductoService] Buscando producto sku={}", sku);
        return productoRepository.findBySku(sku);
    }

    public List<Producto> buscarPorNombre(String nombre) {
        log.info("[ProductoService] Buscando productos con nombre={}", nombre);
        return productoRepository.findByNombreContainingIgnoreCase(nombre);
    }

    public List<Producto> listarPorCategoria(Long idCategoria) {
        log.info("[ProductoService] Listando productos por categoria id={}", idCategoria);
        return productoRepository.findByCategoria_Id(idCategoria);
    }

    public List<Producto> listarPorMarca(Long idMarca) {
        log.info("[ProductoService] Listando productos por marca id={}", idMarca);
        return productoRepository.findByMarca_Id(idMarca);
    }

    public List<Producto> listarPorCategoriaYMarca(Long idCategoria, Long idMarca) {
        log.info("[ProductoService] Filtrando por categoria={} y marca={}", idCategoria, idMarca);
        return productoRepository.findByCategoriaAndMarca(idCategoria, idMarca);
    }

    @Transactional
    public ProductoDTO crear(Producto producto, Long idCategoria, Long idMarca) {
        log.info("[ProductoService] Creando producto sku={}", producto.getSku());

        if (productoRepository.existsBySku(producto.getSku())) {
            log.warn("[ProductoService] SKU duplicado: {}", producto.getSku());
            throw new IllegalArgumentException(
                "Ya existe un producto con el SKU: " + producto.getSku()
            );
        }

        Categoria categoria = categoriaRepository.buscarPorId(idCategoria)
            .orElseThrow(() -> new RuntimeException(
                "Categoria no encontrada con id: " + idCategoria
            ));
        producto.setCategoria(categoria);

        Marca marca = marcaRepository.buscarPorId(idMarca)
            .orElseThrow(() -> new RuntimeException(
                "Marca no encontrada con id: " + idMarca
            ));
        producto.setMarca(marca);

        Producto guardado = productoRepository.guardar(producto);
        log.info("[ProductoService] Producto creado con id={}", guardado.getId());
        return toDTO(guardado);
    }

    @Transactional
    public ProductoDTO actualizar(Long id, Producto datos) {
        Producto producto = productoRepository.buscarPorId(id)
            .orElseThrow(() -> new RuntimeException("Producto no encontrado: " + id));

        if (datos.getNombre()      != null) producto.setNombre(datos.getNombre());
        if (datos.getDescripcion() != null) producto.setDescripcion(datos.getDescripcion());
        if (datos.getPrecio()      != null) producto.setPrecio(datos.getPrecio());
        if (datos.getStock()       != null) producto.setStock(datos.getStock());

        log.info("[ProductoService] Producto actualizado id={}", id);
        return toDTO(productoRepository.guardar(producto));
    }

    @Transactional
    public void eliminar(Long id) {
        if (!productoRepository.existePorId(id)) {
            throw new RuntimeException("Producto no encontrado con id: " + id);
        }
        List<ImagenProducto> imagenesAsociadas = imagenProductoRepository.findByProducto_Id(id);
        if (!imagenesAsociadas.isEmpty()) {
            throw new IllegalStateException(
                "No se puede eliminar el producto porque tiene " +
                imagenesAsociadas.size() + " imagen(es) asociada(s). Eliminalas primero."
            );
        }
        productoRepository.eliminarPorId(id);
        log.info("[ProductoService] Producto eliminado id={}", id);
    }

    // ── CRUD Categoria ───────────────────────────────────────────────────────

    public List<Categoria> listarCategorias() {
        return categoriaRepository.listarTodos();
    }

    public List<Categoria> listarCategoriasActivas() {
        return categoriaRepository.findByActivaTrue();
    }

    public Optional<Categoria> buscarCategoriaPorId(Long id) {
        return categoriaRepository.buscarPorId(id);
    }

    @Transactional
    public Categoria crearCategoria(Categoria categoria) {
        if (categoriaRepository.existsByNombre(categoria.getNombre())) {
            throw new IllegalArgumentException(
                "Ya existe una categoria con el nombre: " + categoria.getNombre()
            );
        }
        Categoria guardada = categoriaRepository.guardar(categoria);
        log.info("[ProductoService] Categoria creada: {}", guardada.getNombre());
        return guardada;
    }

    @Transactional
    public Categoria actualizarCategoria(Long id, Categoria datos) {
        Categoria categoria = categoriaRepository.buscarPorId(id)
            .orElseThrow(() -> new RuntimeException("Categoria no encontrada: " + id));

        if (datos.getNombre()      != null) categoria.setNombre(datos.getNombre());
        if (datos.getDescripcion() != null) categoria.setDescripcion(datos.getDescripcion());
        if (datos.getActiva()      != null) categoria.setActiva(datos.getActiva());

        return categoriaRepository.guardar(categoria);
    }

    @Transactional
    public void eliminarCategoria(Long id) {
        if (!categoriaRepository.existePorId(id)) {
            throw new RuntimeException("Categoria no encontrada: " + id);
        }
        List<Producto> productosAsociados = productoRepository.findByCategoria_Id(id);
        if (!productosAsociados.isEmpty()) {
            throw new IllegalStateException(
                "No se puede eliminar la categoria porque tiene " +
                productosAsociados.size() + " producto(s) asociado(s)"
            );
        }
        categoriaRepository.eliminarPorId(id);
        log.info("[ProductoService] Categoria eliminada id={}", id);
    }

    // ── CRUD Marca ───────────────────────────────────────────────────────────

    public List<Marca> listarMarcas() {
        return marcaRepository.listarTodos();
    }

    public Optional<Marca> buscarMarcaPorId(Long id) {
        return marcaRepository.buscarPorId(id);
    }

    @Transactional
    public Marca crearMarca(Marca marca) {
        if (marcaRepository.existsByNombre(marca.getNombre())) {
            throw new IllegalArgumentException(
                "Ya existe una marca con el nombre: " + marca.getNombre()
            );
        }
        Marca guardada = marcaRepository.guardar(marca);
        log.info("[ProductoService] Marca creada: {}", guardada.getNombre());
        return guardada;
    }

    @Transactional
    public Marca actualizarMarca(Long id, Marca datos) {
        Marca marca = marcaRepository.buscarPorId(id)
            .orElseThrow(() -> new RuntimeException("Marca no encontrada: " + id));

        if (datos.getNombre()     != null) marca.setNombre(datos.getNombre());
        if (datos.getPaisOrigen() != null) marca.setPaisOrigen(datos.getPaisOrigen());

        return marcaRepository.guardar(marca);
    }

    @Transactional
    public void eliminarMarca(Long id) {
        if (!marcaRepository.existePorId(id)) {
            throw new RuntimeException("Marca no encontrada: " + id);
        }
        List<Producto> productosAsociados = productoRepository.findByMarca_Id(id);
        if (!productosAsociados.isEmpty()) {
            throw new IllegalStateException(
                "No se puede eliminar la marca porque tiene " +
                productosAsociados.size() + " producto(s) asociado(s)"
            );
        }
        marcaRepository.eliminarPorId(id);
        log.info("[ProductoService] Marca eliminada id={}", id);
    }

    // ── CRUD Imagen ──────────────────────────────────────────────────────────

    public List<ImagenProducto> listarImagenesPorProducto(Long idProducto) {
        return imagenProductoRepository.findByProducto_Id(idProducto);
    }

    public Optional<ImagenProducto> buscarImagenPrincipal(Long idProducto) {
        return imagenProductoRepository.findByProducto_IdAndPrincipalTrue(idProducto);
    }

    @Transactional
    public ImagenProducto agregarImagen(ImagenProducto imagen, Long idProducto) {
        Producto producto = productoRepository.buscarPorId(idProducto)
            .orElseThrow(() -> new RuntimeException("Producto no encontrado: " + idProducto));

        imagen.setProducto(producto);
        ImagenProducto guardada = imagenProductoRepository.guardar(imagen);
        log.info("[ProductoService] Imagen agregada al producto id={}", idProducto);
        return guardada;
    }

    @Transactional
    public void eliminarImagen(Long id) {
        if (!imagenProductoRepository.existePorId(id)) {
            throw new RuntimeException("Imagen no encontrada: " + id);
        }
        imagenProductoRepository.eliminarPorId(id);
        log.info("[ProductoService] Imagen eliminada id={}", id);
    }
}
