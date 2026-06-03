package taskmanager.manager;

import taskmanager.model.Tarea;
import java.util.ArrayList;
import java.util.List;

public class GestorTareas {

    private List<Tarea> tareas = new ArrayList<>();

    public void agregar(Tarea tarea) {
        tareas.add(tarea);
    }

    public boolean eliminar(int id) {
        return tareas.removeIf(t -> t.getId() == id);
    }

    public Tarea buscarPorId(int id) {
        return tareas.stream()
                .filter(t -> t.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public List<Tarea> getTareas() {
        return tareas;
    }

    public void mostrar() {
        if (tareas.isEmpty()) {
            System.out.println("  No hay tareas.");
            return;
        }
        tareas.forEach(t -> System.out.println("  " + t));
    }
}