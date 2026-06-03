package taskmanager.memento;

import taskmanager.model.Tarea;
import java.util.ArrayList;
import java.util.List;

public class MementoEstado {

    private List<Tarea> snapshot;
    private String etiqueta;

    public MementoEstado(List<Tarea> tareas, String etiqueta) {
        this.etiqueta = etiqueta;
        // copiamos cada tarea para que el snapshot sea independiente
        this.snapshot = new ArrayList<>();
        for (Tarea t : tareas) {
            snapshot.add(new Tarea(t.getId(), t.getTitulo(), t.isCompletada()));
        }
    }

    public List<Tarea> getSnapshot() {
        return snapshot;
    }

    public String getEtiqueta() {
        return etiqueta;
    }
}
