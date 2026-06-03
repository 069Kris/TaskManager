package taskmanager.command;

import taskmanager.manager.GestorTareas;
import taskmanager.model.Tarea;

public class EditarTareaComando implements Comando {

    private GestorTareas gestor;
    private int id;
    private String nuevoTitulo;
    private Tarea tarea;
    private String tituloAnterior;

    public EditarTareaComando(GestorTareas gestor, int id, String nuevoTitulo) {
        this.gestor = gestor;
        this.id = id;
        this.nuevoTitulo = nuevoTitulo;
    }

    @Override
    public void ejecutar() {
        tarea = gestor.buscarPorId(id);
        if (tarea == null) {
            System.out.println("  No existe tarea con id " + id);
            return;
        }
        tituloAnterior = tarea.getTitulo();
        tarea.setTitulo(nuevoTitulo);
        System.out.println("  Tarea editada: \"" + tituloAnterior + "\" → \"" + nuevoTitulo + "\"");
    }

    @Override
    public void deshacer() {
        if (tarea != null) {
            tarea.setTitulo(tituloAnterior);
            System.out.println("  Deshecho: título restaurado a \"" + tituloAnterior + "\"");
        }
    }
}