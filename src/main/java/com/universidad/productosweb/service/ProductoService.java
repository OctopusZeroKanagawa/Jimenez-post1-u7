package com.universidad.productosweb.service;

import com.universidad.productosweb.model.Producto;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Servicio que actúa como repositorio en memoria para los productos.
 * Utiliza un LinkedHashMap para mantener el orden de inserción.
 *
 * @Service indica a Spring que debe gestionar esta clase como un
 * componente singleton: se crea una sola instancia compartida por
 * todos los controladores que la inyecten.
 */
@Service
public class ProductoService {

    // Almacén en memoria: clave = id, valor = producto
    private final Map<Long, Producto> productos = new LinkedHashMap<>();

    // Contador autoincremental para asignar IDs únicos
    private Long contadorId = 1L;

    /**
     * Constructor: carga tres productos de ejemplo al iniciar la app.
     */
    public ProductoService() {
        guardar(new Producto(null, "Laptop",   "Laptop 15 pulgadas 16GB RAM", 1299.99));
        guardar(new Producto(null, "Mouse",    "Mouse inalámbrico ergonómico",   29.99));
        guardar(new Producto(null, "Teclado",  "Teclado mecánico TKL",           89.99));
    }

    /**
     * Retorna todos los productos almacenados como lista.
     */
    public List<Producto> obtenerTodos() {
        return new ArrayList<>(productos.values());
    }

    /**
     * Busca un producto por su ID.
     * Devuelve Optional.empty() si no existe, evitando NullPointerException.
     */
    public Optional<Producto> buscarPorId(Long id) {
        return Optional.ofNullable(productos.get(id));
    }

    /**
     * Guarda un producto (INSERT si id == null, UPDATE si ya tiene id).
     * Devuelve el producto con su id asignado.
     */
    public Producto guardar(Producto producto) {
        if (producto.getId() == null) {
            // Nuevo producto: asignar ID y registrar
            producto.setId(contadorId++);
        }
        productos.put(producto.getId(), producto);
        return producto;
    }

    /**
     * Elimina el producto con el ID indicado.
     * Si el ID no existe, no lanza excepción (operación idempotente).
     */
    public void eliminar(Long id) {
        productos.remove(id);
    }
}
