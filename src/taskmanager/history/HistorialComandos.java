package taskmanager.history;

import taskmanager.command.Comando;
import java.util.Stack;

public class HistorialComandos {

    private Stack<Comando> stackUndo = new Stack<>();
    private Stack<Comando> stackRedo = new Stack<>();

    public void ejecutar(Comando comando) {
        comando.ejecutar();
        stackUndo.push(comando);
        stackRedo.clear(); // nueva acción invalida el redo
    }

    public void deshacer() {
        if (stackUndo.isEmpty()) {
            System.out.println("  Nada que deshacer.");
            return;
        }
        Comando comando = stackUndo.pop();
        comando.deshacer();
        stackRedo.push(comando);
    }

    public void rehacer() {
        if (stackRedo.isEmpty()) {
            System.out.println("  Nada que rehacer.");
            return;
        }
        Comando comando = stackRedo.pop();
        comando.ejecutar();
        stackUndo.push(comando);
    }

    public void mostrarHistorial() {
        if (stackUndo.isEmpty()) {
            System.out.println("  Historial vacío.");
            return;
        }
        System.out.println("  Acciones realizadas (" + stackUndo.size() + "):");
        // toArray para no destruir el stack al recorrerlo
        Object[] comandos = stackUndo.toArray();
        for (int i = comandos.length - 1; i >= 0; i--) {
            System.out.println("  " + (comandos.length - i) + ". " + comandos[i].getClass().getSimpleName());
        }
    }
}