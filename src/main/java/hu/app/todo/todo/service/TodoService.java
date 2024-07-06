package hu.app.todo.todo.service;

import hu.app.todo.api.model.NewTodoModel;
import hu.app.todo.api.model.TodoModel;
import hu.app.todo.todo.entity.Todo;
import hu.app.todo.todo.mapper.TodoMapper;
import hu.app.todo.todo.persistence.TodoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TodoService {

  private final TodoRepository todoRepository;
  private final TodoMapper todoMapper;

  public TodoModel createTodo(NewTodoModel newTodoModel) {
    // TODO: validation
    log.debug("Creating new todo: {}", newTodoModel);
    Todo newTodo = todoMapper.newModelToEntity(newTodoModel);
    todoRepository.save(newTodo);
    log.debug("Todo created: {}", newTodo);

    return todoMapper.entityToModel(newTodo);
  }
}
