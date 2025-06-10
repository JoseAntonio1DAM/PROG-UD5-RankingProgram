package com.microsoft.gestion;

import javax.swing.*;

import com.microsoft.lugares.Playa;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Clase GestorDePlayas
 * Gestiona operaciones CRUD sobre objetos Playa usando Swing.
 */
public class GestorDePlayas {

    private static final ArrayList<Playa> listaDePlayas = new ArrayList<>();

    public static void menuPrincipal() {
        boolean continuar = true;

        while (continuar) {
            String opcion = JOptionPane.showInputDialog("""
                    GESTOR DE PLAYAS - MENÚ
                    1. Añadir playa
                    2. Editar playa
                    3. Mostrar playas
                    4. Eliminar playa
                    Q. Salir
                    Elige una opción:
                    """);

            if (opcion == null)
                continue;

            switch (opcion.trim().toUpperCase()) {
                case "1" -> anadirPlaya();
                case "2" -> editarPlaya();
                case "3" -> mostrarPlayas();
                case "4" -> eliminarPlaya();
                case "Q" -> continuar = false;
                default -> JOptionPane.showMessageDialog(null, "Opción no válida.");
            }
        }
    }

    public static void anadirPlaya() {
        try {
            String nombre = JOptionPane.showInputDialog("Nombre de la playa:");
            String ubicacion = JOptionPane.showInputDialog("Ubicación:");
            String descripcion = JOptionPane.showInputDialog("Descripción:");
            int puntuacion = pedirPuntuacion();

            Playa nueva = new Playa(nombre, ubicacion, descripcion, puntuacion);
            listaDePlayas.add(nueva);
            JOptionPane.showMessageDialog(null, "Playa añadida correctamente.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al añadir la playa.");
        }
    }

    public static void editarPlaya() {
        if (listaDePlayas.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay playas para editar.");
            return;
        }

        String nombre = JOptionPane.showInputDialog("Introduce el nombre de la playa a editar:");
        Playa playa = buscarPlayaPorNombre(nombre);

        if (playa != null) {
            try {
                String nuevaUbicacion = JOptionPane.showInputDialog("Nueva ubicación:", playa.getUbicacion());
                String nuevaDescripcion = JOptionPane.showInputDialog("Nueva descripción:", playa.getDescripcion());
                int nuevaPuntuacion = pedirPuntuacion();

                playa.setPuntuacion(nuevaPuntuacion);
                playa = new Playa(playa.getNombre(), nuevaUbicacion, nuevaDescripcion, nuevaPuntuacion);
                int index = listaDePlayas.indexOf(buscarPlayaPorNombre(nombre));
                listaDePlayas.set(index, playa);

                JOptionPane.showMessageDialog(null, "Playa editada correctamente.");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al editar la playa.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Playa no encontrada.");
        }
    }

    public static void mostrarPlayas() {
        if (listaDePlayas.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay playas registradas.");
            return;
        }

        listaDePlayas.sort(Comparator.comparingInt(Playa::getPuntuacion).reversed());
        StringBuilder sb = new StringBuilder("Ranking de playas:\n");

        for (Playa playa : listaDePlayas) {
            sb.append(playa).append("\n");
        }

        JOptionPane.showMessageDialog(null, sb.toString());
    }

    public static void eliminarPlaya() {
        if (listaDePlayas.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay playas para eliminar.");
            return;
        }

        String nombre = JOptionPane.showInputDialog("Nombre de la playa a eliminar:");
        Playa playa = buscarPlayaPorNombre(nombre);

        if (playa != null) {
            listaDePlayas.remove(playa);
            JOptionPane.showMessageDialog(null, "Playa eliminada correctamente.");
        } else {
            JOptionPane.showMessageDialog(null, "Playa no encontrada.");
        }
    }

    private static Playa buscarPlayaPorNombre(String nombre) {
        for (Playa playa : listaDePlayas) {
            if (playa.getNombre().equalsIgnoreCase(nombre.trim())) {
                return playa;
            }
        }
        return null;
    }

    private static int pedirPuntuacion() throws NumberFormatException {
        int puntuacion;
        do {
            String entrada = JOptionPane.showInputDialog("Puntuación (1-5):");
            if (entrada == null)
                throw new NumberFormatException();
            puntuacion = Integer.parseInt(entrada);
        } while (puntuacion < 1 || puntuacion > 5);
        return puntuacion;
    }
}
