package hu.app.todo.todo.mapper;

import hu.app.todo.api.model.NewTodoModel;
import hu.app.todo.api.model.TodoModel;
import hu.app.todo.api.model.TodoStatusModel;
import hu.app.todo.todo.TodoStatus;
import hu.app.todo.todo.entity.Todo;
import org.springframework.stereotype.Component;

@Component
public class TodoMapper {

  public Todo newModelToEntity(NewTodoModel newTodoModel) {
    return new Todo()
        .summary(newTodoModel.getSummary())
        .details(newTodoModel.getDetails())
        .status(TodoStatus.CREATED);
  }

  public TodoModel entityToModel(Todo entity) {
    return new TodoModel()
        .id(entity.getId())
        .updateTime(entity.getUpdateTime())
        .createDate(entity.getCreateDate())
        .summary(entity.summary())
        .details(entity.details())
        .status(TodoStatusModel.fromValue(entity.status().name()));
  }
}
