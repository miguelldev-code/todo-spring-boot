package miguelldev.todo.controller;

import miguelldev.todo.model.Todo;
import miguelldev.todo.repository.TodoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Indica que esta clase maneja peticiones HTTP y devuelve respuestas JSON
@RequestMapping("/api/todos") // Ruta base para las peticiones
@CrossOrigin(origins = "*") // Permite solicitudes desde cualquier origen (útil para frontend local)
public class TodoController {

    private final TodoRepository repo; // Repositorio para interactuar con la base de datos

    // Inyección de dependencias (Spring lo hace automáticamente)
    public TodoController(TodoRepository repo) {
        this.repo = repo;
    }

    @GetMapping // GET /api/todos
    public List<Todo> listarTodos() {
        return repo.findAll(); // Retorna todas las tareas
    }

    @PostMapping // POST /api/todos
    public Todo crear(@RequestBody Todo todo) {
        return repo.save(todo); // Guarda una nueva tarea
    }

    @DeleteMapping("/{id}") // DELETE /api/todos/{id}
    public void eliminar(@PathVariable Long id) {
        repo.deleteById(id); // Elimina la tarea por su ID
    }

    // Este método no tiene anotación, por eso no se usa
    public Todo completar(@PathVariable Long id) {
        Todo todo = repo.findById(id).orElseThrow(); // Busca la tarea
        todo.setCompletado(true); // Marca como completada
        return repo.save(todo); // Guarda el cambio
    }

    @PutMapping("/{id}/completar") // PUT /api/todos/{id}/completar
    public Todo alternarEstado(@PathVariable Long id) {
        Todo todo = repo.findById(id).orElseThrow();
        todo.setCompletado(!todo.isCompletado()); // Cambia el estado: true ↔ false
        return repo.save(todo);
    }
}