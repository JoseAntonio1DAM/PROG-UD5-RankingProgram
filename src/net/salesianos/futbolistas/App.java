package net.salesianos.futbolistas;

import javax.swing.JOptionPane;

public class App {
    private static ManagerDeJugadores manager = new ManagerDeJugadores();

    public static void main(String[] args) {
        boolean salir = false;

        while (!salir) {
            String opcion = JOptionPane.showInputDialog(
                    "⚽ MENÚ RANKING JUGADORES LALIGA ⚽\n\n" +
                            "1. Añadir jugador\n" +
                            "2. Editar jugador\n" +
                            "3. Mostrar ranking\n" +
                            "4. Eliminar jugador\n" +
                            "5. Salir\n\n" +
                            "Selecciona una opción:");

            try {
                switch (opcion) {
                    case "1":
                        añadirJugador();
                        break;
                    case "2":
                        editarJugador();
                        break;
                    case "3":
                        mostrarRanking();
                        break;
                    case "4":
                        eliminarJugador();
                        break;
                    case "5":
                        salir = true;
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Opción no válida");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            }
        }
    }

    private static void añadirJugador() {
        String nombre = JOptionPane.showInputDialog("Nombre del jugador:");
        String equipo = JOptionPane.showInputDialog("Equipo:");
        String posicion = JOptionPane.showInputDialog("Posición:");
        int edad = Integer.parseInt(JOptionPane.showInputDialog("Edad:"));
        int puntuacion = Integer.parseInt(JOptionPane.showInputDialog("Puntuación (1-5):"));
        boolean balonDeOro = JOptionPane.showConfirmDialog(null, "¿Ha ganado Balón de Oro?") == JOptionPane.YES_OPTION;

        if (puntuacion < 1 || puntuacion > 5) {
            throw new IllegalArgumentException("La puntuación debe ser entre 1 y 5");
        }

        Jugador nuevoJugador = new Jugador(nombre, equipo, posicion, edad, puntuacion, balonDeOro);
        manager.añadirJugador(nuevoJugador);
        JOptionPane.showMessageDialog(null, "Jugador añadido correctamente!");
    }

    private static void mostrarRanking() {
        StringBuilder ranking = new StringBuilder("🏆 TOP JUGADORES LALIGA 🏆\n\n");
        for (Jugador jugador : manager.obtenerRanking()) {
            ranking.append(jugador.toString()).append("\n\n");
        }
        JOptionPane.showMessageDialog(null, ranking.toString());
    }

    private static void editarJugador() {
        String nombre = JOptionPane.showInputDialog("Nombre del jugador a editar:");
        Jugador jugador = manager.buscarJugador(nombre);

        if (jugador == null) {
            JOptionPane.showMessageDialog(null, "Jugador no encontrado");
            return;
        }

        // Mostrar menú de edición
        String opcion = JOptionPane.showInputDialog(
                "Editando a " + jugador.getNombre() + "\n\n" +
                        "1. Cambiar equipo\n" +
                        "2. Cambiar posición\n" +
                        "3. Cambiar edad\n" +
                        "4. Cambiar puntuación\n" +
                        "5. Cambiar Balón de Oro\n" +
                        "6. Volver");

        switch (opcion) {
            case "1":
                jugador.setEquipo(JOptionPane.showInputDialog("Nuevo equipo:"));
                break;
            case "2":
                jugador.setPosición(JOptionPane.showInputDialog("Nueva posición:"));
                break;
            case "3":
                jugador.setEdad(Integer.parseInt(JOptionPane.showInputDialog("Nueva edad:")));
                break;
            case "4":
                int nuevaPuntuacion = Integer.parseInt(JOptionPane.showInputDialog("Nueva puntuación (1-5):"));
                if (nuevaPuntuacion < 1 || nuevaPuntuacion > 5) {
                    throw new IllegalArgumentException("Puntuación inválida");
                }
                jugador.setPuntuación(nuevaPuntuacion);
                break;
            case "5":
                jugador.setBalonDeOro(
                        JOptionPane.showConfirmDialog(null, "¿Ha ganado Balón de Oro?") == JOptionPane.YES_OPTION);
                break;
        }

        JOptionPane.showMessageDialog(null, "Jugador actualizado correctamente!");
    }

    private static void eliminarJugador() {
        String nombre = JOptionPane.showInputDialog("Nombre del jugador a eliminar:");
        if (manager.eliminarJugador(nombre)) {
            JOptionPane.showMessageDialog(null, "Jugador eliminado correctamente!");
        } else {
            JOptionPane.showMessageDialog(null, "Jugador no encontrado");
        }
    }
}