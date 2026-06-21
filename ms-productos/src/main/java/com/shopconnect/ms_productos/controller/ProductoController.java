package com.shopconnect.ms_productos.controller;

import com.shopconnect.ms_productos.dto.ProductoDTO;
import com.shopconnect.ms_productos.model.Categoria;
import com.shopconnect.ms_productos.model.ImagenProducto;
import com.shopconnect.ms_productos.model.Marca;
import com.shopconnect.ms_productos.model.Producto;
import com.shopconnect.ms_productos.service.ProductoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    // ── ENDPOINTS Producto 

    @GetMapping
    public ResponseEntity<List<ProductoDTO>> listarTodos() {
        return ResponseEntity.ok(productoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        return productoService.buscarPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/sku/{sku}")
    public ResponseEntity<?> buscarPorSku(@PathVariable String sku) {
        return productoService.buscarPorSku(sku)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Producto>> buscarPorNombre(@RequestParam String nombre) {
        return ResponseEntity.ok(productoService.buscarPorNombre(nombre));
    }

    @GetMapping("/categoria/{idCategoria}")
    public ResponseEntity<List<Producto>> listarPorCategoria(@PathVariable Long idCategoria) {
        return ResponseEntity.ok(productoService.listarPorCategoria(idCategoria));
    }

    @GetMapping("/marca/{idMarca}")
    public ResponseEntity<List<Producto>> listarPorMarca(@PathVariable Long idMarca) {
        return ResponseEntity.ok(productoService.listarPorMarca(idMarca));
    }

    @GetMapping("/filtrar")
    public ResponseEntity<List<Producto>> filtrarPorCategoriaYMarca(
            @RequestParam Long idCategoria,
            @RequestParam Long idMarca) {
        return ResponseEntity.ok(productoService.listarPorCategoriaYMarca(idCategoria, idMarca));
    }

    @PostMapping
    public ResponseEntity<ProductoDTO> crear(
            @Valid @RequestBody Producto producto,
            @RequestParam Long idCategoria,
            @RequestParam Long idMarca) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(productoService.crear(producto, idCategoria, idMarca));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoDTO> actualizar(
            @PathVariable Long id,
            @RequestBody Producto datos) {
        return ResponseEntity.ok(productoService.actualizar(id, datos));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        productoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    // ── ENDPOINTS Categoria 

    @GetMapping("/categorias")
    public ResponseEntity<List<Categoria>> listarCategorias() {
        return ResponseEntity.ok(productoService.listarCategorias());
    }

    @GetMapping("/categorias/activas")
    public ResponseEntity<List<Categoria>> listarCategoriasActivas() {
        return ResponseEntity.ok(productoService.listarCategoriasActivas());
    }

    @GetMapping("/categorias/{id}")
    public ResponseEntity<?> buscarCategoriaPorId(@PathVariable Long id) {
        return productoService.buscarCategoriaPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/categorias")
    public ResponseEntity<Categoria> crearCategoria(@Valid @RequestBody Categoria categoria) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(productoService.crearCategoria(categoria));
    }

    @PutMapping("/categorias/{id}")
    public ResponseEntity<Categoria> actualizarCategoria(
            @PathVariable Long id,
            @RequestBody Categoria datos) {
        return ResponseEntity.ok(productoService.actualizarCategoria(id, datos));
    }

    @DeleteMapping("/categorias/{id}")
    public ResponseEntity<Void> eliminarCategoria(@PathVariable Long id) {
        productoService.eliminarCategoria(id);
        return ResponseEntity.noContent().build();
    }

    // ── ENDPOINTS Marca ──────────────────────────────────────────────────────

    @GetMapping("/marcas")
    public ResponseEntity<List<Marca>> listarMarcas() {
        return ResponseEntity.ok(productoService.listarMarcas());
    }

    @GetMapping("/marcas/{id}")
    public ResponseEntity<?> buscarMarcaPorId(@PathVariable Long id) {
        return productoService.buscarMarcaPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/marcas")
    public ResponseEntity<Marca> crearMarca(@Valid @RequestBody Marca marca) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(productoService.crearMarca(marca));
    }

    @PutMapping("/marcas/{id}")
    public ResponseEntity<Marca> actualizarMarca(
            @PathVariable Long id,
            @RequestBody Marca datos) {
        return ResponseEntity.ok(productoService.actualizarMarca(id, datos));
    }

    @DeleteMapping("/marcas/{id}")
    public ResponseEntity<Void> eliminarMarca(@PathVariable Long id) {
        productoService.eliminarMarca(id);
        return ResponseEntity.noContent().build();
    }

    // ── ENDPOINTS Imagen ─────────────────────────────────────────────────────

    @GetMapping("/imagenes/producto/{idProducto}")
    public ResponseEntity<List<ImagenProducto>> listarImagenes(@PathVariable Long idProducto) {
        return ResponseEntity.ok(productoService.listarImagenesPorProducto(idProducto));
    }

    @GetMapping("/imagenes/producto/{idProducto}/principal")
    public ResponseEntity<?> buscarImagenPrincipal(@PathVariable Long idProducto) {
        return productoService.buscarImagenPrincipal(idProducto)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/imagenes")
    public ResponseEntity<ImagenProducto> agregarImagen(
            @Valid @RequestBody ImagenProducto imagen,
            @RequestParam Long idProducto) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(productoService.agregarImagen(imagen, idProducto));
    }

    @DeleteMapping("/imagenes/{id}")
    public ResponseEntity<Void> eliminarImagen(@PathVariable Long id) {
        productoService.eliminarImagen(id);
        return ResponseEntity.noContent().build();
    }
}
