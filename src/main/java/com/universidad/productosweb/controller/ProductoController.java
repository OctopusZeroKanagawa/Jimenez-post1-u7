package com.universidad.productosweb.controller;

import com.universidad.productosweb.model.Producto;
import com.universidad.productosweb.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador MVC para la gestión de productos.
 *
 * Implementa el patrón PRG (Post/Redirect/Get):
 *   - Las operaciones de escritura (guardar, eliminar) se hacen vía POST o GET
 *     y SIEMPRE terminan con un redirect a /productos.
 *   - Esto evita que el navegador reenvíe el formulario al pulsar F5.
 *
 * @Controller indica que esta clase maneja peticiones HTTP y devuelve
 * nombres de vistas Thymeleaf (no JSON).
 *
 * @RequestMapping("/productos") prefija todas las rutas del controlador.
 */
@Controller
@RequestMapping("/productos")
public class ProductoController {

    /**
     * @Autowired inyecta automáticamente la instancia singleton
     * de ProductoService gestionada por Spring.
     */
    @Autowired
    private ProductoService servicio;

    /**
     * GET /productos
     * Lista todos los productos y los pasa al modelo para la vista.
     */
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("productos", servicio.obtenerTodos());
        return "productos/lista"; // → src/main/resources/templates/productos/lista.html
    }

    /**
     * GET /productos/nuevo
     * Muestra el formulario vacío para crear un nuevo producto.
     * Se añade un Producto vacío al modelo para que Thymeleaf
     * pueda hacer el binding con th:object.
     */
    @GetMapping("/nuevo")
    public String formularioNuevo(Model model) {
        model.addAttribute("producto", new Producto());
        model.addAttribute("accion", "Crear");
        return "productos/formulario";
    }

    /**
     * GET /productos/editar/{id}
     * Muestra el formulario prellenado con los datos del producto a editar.
     * Lanza RuntimeException si el ID no existe (Spring lo mapea a error 500).
     */
    @GetMapping("/editar/{id}")
    public String formularioEditar(@PathVariable Long id, Model model) {
        Producto producto = servicio.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado: " + id));
        model.addAttribute("producto", producto);
        model.addAttribute("accion", "Editar");
        return "productos/formulario";
    }

    /**
     * POST /productos/guardar
     * Recibe los datos del formulario, los guarda y redirige a la lista.
     * @ModelAttribute enlaza automáticamente los campos del formulario
     * con los atributos del objeto Producto.
     *
     * El "redirect:" es la clave del patrón PRG: el navegador recibe
     * un HTTP 302 y hace un GET a /productos, evitando reenvíos.
     */
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Producto producto) {
        servicio.guardar(producto);
        return "redirect:/productos";
    }

    /**
     * GET /productos/eliminar/{id}
     * Elimina el producto indicado y redirige a la lista.
     * Se usa GET (en vez de DELETE) para simplificar los enlaces HTML.
     */
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        servicio.eliminar(id);
        return "redirect:/productos";
    }
}
