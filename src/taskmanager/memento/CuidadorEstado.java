package taskmanager.memento;

import taskmanager.manager.GestorTareas;
import taskmanager.model.Tarea;
import java.util.ArrayList;
import java.util.List;

public class CuidadorEstado {

    private List<MementoEstado> puntos = new ArrayList<>();
    private GestorTareas gestor;

    public CuidadorEstado(GestorTareas gestor) {
        this.gestor = gestor;
    }

    public void guardarPunto(String etiqueta) {
        MementoEstado memento = new MementoEstado(gestor.getTareas(), etiqueta);
        puntos.add(memento);
        System.out.println("  Punto guardado: \"" + etiqueta + "\"");
    }

    public void restaurarPunto(int indice) {
        if (indice < 0 || indice >= puntos.size()) {
            System.out.println("  Índice inválido.");
            return;
        }
        MementoEstado memento = puntos.get(indice);
        // limpiamos y reconstruimos la lista del gestor
        gestor.getTareas().clear();
        for (Tarea t : memento.getSnapshot()) {
            gestor.getTareas().add(t);
        }
        System.out.println("  Estado restaurado: \"" + memento.getEtiqueta() + "\"");
    }

    public void listarPuntos() {
        if (puntos.isEmpty()) {
            System.out.println("  No hay puntos guardados.");
            return;
        }
        for (int i = 0; i < puntos.size(); i++) {
            System.out.println("  [" + i + "] " + puntos.get(i).getEtiqueta());
        }
    }
}