package miguelldev.todo.model;

import jakarta.persistence.*;

@Entity // Define que esta clase es una tabla en la BD
public class Todo {
    @Id // Llave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incrementa el ID
    private Long id;

    private String tarea; // Texto de la tarea
    private boolean completado = false; // Por defecto, no está completada

    // Métodos getter y setter para acceder y modificar los atributos

    public Long getId() {
        return id;
    }

    public String getTarea() {
        return tarea;
    }

    public void setTarea(String tarea) {
        this.tarea = tarea;
    }

    public boolean isCompletado() {
        return completado;
    }

    public void setCompletado(boolean completado) {
        this.completado = completado;
    }
}
