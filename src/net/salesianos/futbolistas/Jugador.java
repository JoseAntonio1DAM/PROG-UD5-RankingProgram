package net.salesianos.futbolistas;

public class Jugador {
    private int edad;
    private int puntuación;
    private String equipo;
    private String nombre;
    private String posición;
    private boolean balonDeOro;

    // Constructor completo
    public Jugador(String nombre, String equipo, String posición, int edad, int puntuación, boolean balonDeOro) {
        this.nombre = nombre;
        this.equipo = equipo;
        this.posición = posición;
        this.edad = edad;
        this.puntuación = puntuación;
        this.balonDeOro = balonDeOro;
    }

    // Getters y setters

    // Método con override
    @Override
    public String toString() {
        String estrella = balonDeOro ? "🌟" : "";
        return nombre + " (" + equipo + ") - " + posición +
                "\nEdad: " + edad + " | Puntuación: " + "⭐".repeat(puntuación) +
                " " + estrella;
    }
}