package taskmanager;

import taskmanager.command.*;
import taskmanager.history.HistorialComandos;
import taskmanager.manager.GestorTareas;
import taskmanager.memento.CuidadorEstado;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        GestorTareas gestor = new GestorTareas();
        HistorialComandos historial = new HistorialComandos();
        CuidadorEstado cuidador = new CuidadorEstado(gestor);
        Scanner scanner = new Scanner(System.in);
        boolean ejecutando = true;

        while (ejecutando) {
            System.out.println("\n╔══════════════════════════════╗");
            System.out.println("║      GESTOR DE TAREAS        ║");
            System.out.println("╠══════════════════════════════╣");
            System.out.println("║  1. Ver tareas               ║");
            System.out.println("║  2. Agregar tarea            ║");
            System.out.println("║  3. Eliminar tarea           ║");
            System.out.println("║  4. Completar tarea          ║");
            System.out.println("║  5. Editar tarea             ║");
            System.out.println("║  6. Deshacer (undo)          ║");
            System.out.println("║  7. Rehacer (redo)           ║");
            System.out.println("║  8. Ver historial            ║");
            System.out.println("║  9. Guardar punto            ║");
            System.out.println("║ 10. Restaurar punto          ║");
            System.out.println("║  0. Salir                    ║");
            System.out.println("╚══════════════════════════════╝");
            System.out.print("  Opción: ");

            String input = scanner.nextLine().trim();

            switch (input) {
                case "1":
                    System.out.println("\n── Tareas ──");
                    gestor.mostrar();
                    break;

                case "2":
                    System.out.print("  Título: ");
                    String titulo = scanner.nextLine().trim();
                    if (!titulo.isEmpty())
                        historial.ejecutar(new AgregarTareaComando(gestor, titulo));
                    break;

                case "3":
                    System.out.print("  ID a eliminar: ");
                    historial.ejecutar(new EliminarTareaComando(gestor, leerInt(scanner)));
                    break;

                case "4":
                    System.out.print("  ID a completar: ");
                    historial.ejecutar(new CompletarTareaComando(gestor, leerInt(scanner)));
                    break;

                case "5":
                    System.out.print("  ID a editar: ");
                    int idEditar = leerInt(scanner);
                    System.out.print("  Nuevo título: ");
                    String nuevoTitulo = scanner.nextLine().trim();
                    if (!nuevoTitulo.isEmpty())
                        historial.ejecutar(new EditarTareaComando(gestor, idEditar, nuevoTitulo));
                    break;

                case "6":
                    historial.deshacer();
                    break;

                case "7":
                    historial.rehacer();
                    break;

                case "8":
                    System.out.println("\n── Historial ──");
                    historial.mostrarHistorial();
                    break;

                case "9":
                    System.out.print("  Nombre del punto: ");
                    String etiqueta = scanner.nextLine().trim();
                    if (!etiqueta.isEmpty())
                        cuidador.guardarPunto(etiqueta);
                    break;

                case "10":
                    System.out.println("\n── Puntos guardados ──");
                    cuidador.listarPuntos();
                    System.out.print("  Índice a restaurar: ");
                    cuidador.restaurarPunto(leerInt(scanner));
                    break;

                case "0":
                    ejecutando = false;
                    System.out.println("\n  ¡Hasta luego!");
                    break;

                default:
                    System.out.println("  Opción no válida.");
            }
        }
        scanner.close();
    }

    private static int leerInt(Scanner scanner) {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}