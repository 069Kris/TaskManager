package taskmanager.command;

import taskmanager.manager.GestorTareas;
import taskmanager.model.Tarea;

public class EliminarTareaComando implements Comando {

    private GestorTareas gestor;
    private int id;
    private Tarea tareaEliminada;

    public EliminarTareaComando(GestorTareas gestor, int id) {
        this.gestor = gestor;
        this.id = id;
    }

    @Override
    public void ejecutar() {
        tareaEliminada = gestor.buscarPorId(id); //se guarda la tarea antes de eliminarla, para poder usarla en deshacer()
        if (tareaEliminada == null) {
            System.out.println(" No existe tarea con id " + id);
            return;
        }
        gestor.eliminar(id);
        System.out.println("  Tarea eliminada: " + tareaEliminada);
    }

    @Override
    public void deshacer() {
        if (tareaEliminada != null) { 
            gestor.agregar(tareaEliminada);
            System.out.println(" Deshecho: se restauró \"" + tareaEliminada.getTitulo() + "\"");
        }
    }
}