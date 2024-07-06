package hu.app.todo.todo.data;

import hu.app.todo.api.model.NewTodoModel;
import hu.app.todo.todo.TodoStatus;
import hu.app.todo.todo.entity.Todo;
import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.test.util.ReflectionTestUtils;

public class TestTodoFactory {

  public static NewTodoModel getNewModel() {
    return new NewTodoModel().summary("Important task").details("I must finish this");
  }

  public static Todo getTodoEntity() {
    Todo todo = new Todo();
    ReflectionTestUtils.setField(todo, "id", UUID.randomUUID());
    ReflectionTestUtils.setField(todo, "updateTime", LocalDateTime.of(2024, 12, 12, 12, 12));
    ReflectionTestUtils.setField(todo, "createDate", LocalDateTime.of(2024, 12, 12, 12, 12));
    todo.summary("Important task").details("I must finish this").status(TodoStatus.CREATED);
    return todo;
  }
}
