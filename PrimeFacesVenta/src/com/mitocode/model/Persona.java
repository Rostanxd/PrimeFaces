package com.mitocode.model;

/**
 * Created by HP on 22/03/2016.
 */
public class Persona {
    private int codigo;
    private String nombre;
    private String sexo;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Persona)) return false;

        Persona persona = (Persona) o;

        return getCodigo() == persona.getCodigo();

    }

    @Override
    public int hashCode() {
        return getCodigo();
    }

    @Override
    public String toString() {
        return "Persona{" +
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

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
}
