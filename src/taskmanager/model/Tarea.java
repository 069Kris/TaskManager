package taskmanager.model;

public class Tarea {

    private static int contadorId = 1; // Asigna ids automáticamente mediante un contador

    private int id;
    private String titulo;
    private boolean completada;

    public Tarea(String titulo) {
        this.id = contadorId++;
        this.titulo = titulo;
        this.completada = false;
    }

    public Tarea(int id, String titulo, boolean completada) { // Constructor extra: para clonar tareas
        this.id = id;
        this.titulo = titulo;
        this.completada = completada;
    }

    public int getId() { return id; }
    public String getTitulo() { return titulo; }
    public boolean isCompletada() { return completada; }

    public void setTitulo(String titulo) { this.titulo = titulo; }
    public void setCompletada(boolean completada) { this.completada = completada; }

    @Override
    public String toString() {
        String estado = completada ? "[X]" : "[ ]";
        return estado + " #" + id + " - " + titulo;
    }
}