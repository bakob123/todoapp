package hu.app.todo.todo.persistence;

import hu.app.todo.todo.entity.Todo;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<Todo, UUID> {}
