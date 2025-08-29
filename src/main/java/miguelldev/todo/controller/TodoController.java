package miguelldev.todo.controller;

import jakarta.validation.Valid;
import miguelldev.todo.model.Todo;
import miguelldev.todo.repository.TodoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController // Indica que esta clase maneja peticiones HTTP y devuelve respuestas JSON
@RequestMapping("/api/todos") // Ruta base para las peticiones
@CrossOrigin(origins = "http://localhost:3000") // Solo permite solicitudes desde el frontend local
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
    public ResponseEntity<Todo> crear(@Valid @RequestBody Todo todo) {
        Todo todoGuardado = repo.save(todo); // Guarda una nueva tarea
        return ResponseEntity.status(HttpStatus.CREATED).body(todoGuardado);
    }

    @DeleteMapping("/{id}") // DELETE /api/todos/{id}
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (repo.existsById(id)) {
            repo.deleteById(id); // Elimina la tarea por su ID
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/completar") // PUT /api/todos/{id}/completar
    public ResponseEntity<Todo> alternarEstado(@PathVariable Long id) {
        Optional<Todo> optionalTodo = repo.findById(id);
        if (optionalTodo.isPresent()) {
            Todo todo = optionalTodo.get();
            todo.setCompletado(!todo.isCompletado()); // Cambia el estado: true ↔ false
            Todo todoGuardado = repo.save(todo);
            return ResponseEntity.ok(todoGuardado);
        }
        return ResponseEntity.notFound().build();
    }
}