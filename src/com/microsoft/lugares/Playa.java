package com.microsoft.lugares;

public class Playa {
    private String nombre;
    private String ubicacion;
    private String descripcion;
    private int puntuacion;

    public Playa(String nombre, String ubicacion, String descripcion, int puntuacion) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.descripcion = descripcion;
        this.puntuacion = puntuacion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    @Override
    public String toString() {
        return nombre + " / " + ubicacion + " / Puntuación: " + puntuacion;
    }
}
