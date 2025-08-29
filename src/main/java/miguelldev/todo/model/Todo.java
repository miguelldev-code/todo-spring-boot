package miguelldev.todo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity // Define que esta clase es una tabla en la BD
public class Todo {
    @Id // Llave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incrementa el ID
    private Long id;

    @NotBlank(message = "La tarea no puede estar vacía")
    @Size(max = 255, message = "La tarea no puede tener más de 255 caracteres")
    private String tarea; // Texto de la tarea
    
    private boolean completado = false; // Por defecto, no está completada

    // Métodos getter y setter para acceder y modificar los atributos

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
