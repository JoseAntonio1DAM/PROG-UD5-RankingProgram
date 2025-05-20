package net.salesianos.futbolistas;

import java.util.ArrayList;
import java.util.Comparator;

public class ManagerDeJugadores {
    private ArrayList<Jugador> jugadores = new ArrayList<>();

    // Añadir jugador
    public void añadirJugador(Jugador jugador) {
        jugadores.add(jugador);
    }

    // Eliminar jugador por nombre
    public boolean eliminarJugador(String nombre) {
        return jugadores.removeIf(j -> j.getNombre().equalsIgnoreCase(nombre));
    }

    // Buscar jugador por nombre
    public Jugador buscarJugador(String nombre) {
        return jugadores.stream()
                .filter(j -> j.getNombre().equalsIgnoreCase(nombre))
                .findFirst()
                .orElse(null);
    }

    // Obtener ranking ordenado por puntuación
    public ArrayList<Jugador> obtenerRanking() {
        jugadores.sort(Comparator.comparingInt(Jugador::getPuntuación).reversed());
        return new ArrayList<>(jugadores);
    }

    // Obtener todos los jugadores
    public ArrayList<Jugador> getTodosJugadores() {
        return new ArrayList<>(jugadores);
    }
}