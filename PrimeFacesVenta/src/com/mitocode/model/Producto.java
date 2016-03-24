package com.mitocode.model;

/**
 * Created by HP on 22/03/2016.
 */
public class Producto {
    private int codigo;
    private String nombre;
    private double precio;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Producto producto = (Producto) o;

        return codigo == producto.codigo;

    }

    @Override
    public int hashCode() {
        return codigo;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "codigo=" + codigo +
                '}';
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
