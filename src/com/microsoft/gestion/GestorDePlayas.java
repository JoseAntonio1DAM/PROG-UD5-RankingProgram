package com.microsoft.RankingPlayas;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.swing.JOptionPane;

import com.microsoft.lugares.Playa;

public class RankingPlayas {
    private List<Playa> playas;

    public RankingPlayas() {
        playas = new ArrayList<>();
    }

    public void agregarPlaya(Playa playa) {
        playas.add(playa);
    }

    public void editarPlaya(String nombre, int nuevaPuntuacion) {
        for (Playa playa : playas) {
            if (playa.getNombre().equalsIgnoreCase(nombre)) {
                playa.setPuntuacion(nuevaPuntuacion);
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Playa no encontrada.");
    }

    public void mostrarRanking() {
        playas.sort(Comparator.comparingInt(Playa::getPuntuacion).reversed());
        StringBuilder resultado = new StringBuilder("Ranking de Playas:\n");
        for (Playa playa : playas) {
            resultado.append(playa).append("\n");
        }
        JOptionPane.showMessageDialog(null, resultado.toString());
    }

    public void eliminarPlaya(String nombre) {
        playas.removeIf(playa -> playa.getNombre().equalsIgnoreCase(nombre));
    }
}
