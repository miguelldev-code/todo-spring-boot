package miguelldev.todo.repository;

import miguelldev.todo.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

// JpaRepository es una especificación de Java que define un marco de trabajo
// para mapear objetos Java a bases de datos relacionales

// Una interfaz solo define los métodos que deben implementarse, pero no su implementación.
// permitiendo que diferentes clases implementen el mismo contrato de manera diferente.
public interface TodoRepository extends JpaRepository<Todo, Long> {
}
// Extiende JpaRepository para tener CRUD sin escribir código extra
