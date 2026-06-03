package taskmanager.command;

import taskmanager.manager.GestorTareas;
import taskmanager.model.Tarea;

public class AgregarTareaComando implements Comando {

    private GestorTareas gestor;
    private Tarea tarea;

    public AgregarTareaComando(GestorTareas gestor, String titulo) {
        this.gestor = gestor;
        this.tarea = new Tarea(titulo);
    }

    @Override
    public void ejecutar() {
        gestor.agregar(tarea);
        System.out.println("  Tarea agregada: " + tarea);
    }

    @Override
    public void deshacer() {
        gestor.eliminar(tarea.getId());
        System.out.println("  Deshecho: se eliminó \"" + tarea.getTitulo() + "\"");
    }
}