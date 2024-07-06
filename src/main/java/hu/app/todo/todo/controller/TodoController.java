package hu.app.todo.todo.controller;

import hu.app.todo.api.TodosApi;
import hu.app.todo.api.model.NewTodoModel;
import hu.app.todo.api.model.TodoModel;
import hu.app.todo.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TodoController implements TodosApi {

  private final TodoService todoService;

  @Override
  public ResponseEntity<TodoModel> createTodo(NewTodoModel newTodoModel) {
    return ResponseEntity.status(HttpStatus.CREATED).body(todoService.createTodo(newTodoModel));
  }
}
