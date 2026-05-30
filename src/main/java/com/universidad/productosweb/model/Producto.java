package com.universidad.productosweb.model;

/**
 * Modelo que representa un Producto en el sistema.
 * Los getters y setters son requeridos por Thymeleaf para el
 * binding automático entre formularios HTML y objetos Java.
 */
public class Producto {

    private Long id;
    private String nombre;
    private String descripcion;
    private Double precio;

    // Constructor sin argumentos (requerido por Spring para el binding de formularios)
    public Producto() {}

    // Constructor con todos los campos (usado para datos de ejemplo)
    public Producto(Long id, String nombre, String descripcion, Double precio) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    // --- Getters y Setters ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Producto{id=" + id + ", nombre='" + nombre + "', precio=" + precio + "}";
    }
}
