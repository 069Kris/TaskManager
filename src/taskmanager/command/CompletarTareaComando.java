package taskmanager.command;

import taskmanager.manager.GestorTareas;
import taskmanager.model.Tarea;

public class CompletarTareaComando implements Comando {

    private GestorTareas gestor;
    private int id;
    private Tarea tarea;
    private boolean estadoAnterior;

    public CompletarTareaComando(GestorTareas gestor, int id) {
        this.gestor = gestor;
        this.id = id;
    }

    @Override
    public void ejecutar() {
        tarea = gestor.buscarPorId(id);
        if (tarea == null) {
            System.out.println("  No existe tarea con id " + id);
            return;
        }
        estadoAnterior = tarea.isCompletada(); // guardas ANTES de cambiar
        tarea.setCompletada(true);
        System.out.println(" Tarea completada: " + tarea);
    }

    @Override
    public void deshacer() {
        if (tarea != null) {
            tarea.setCompletada(estadoAnterior);
            System.out.println("  Deshecho: tarea \"" + tarea.getTitulo() + "\" volvió a " + (estadoAnterior ? "completada" : "pendiente"));
        }
    }
}