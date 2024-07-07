package hu.app.todo.todo.mapper;

import hu.app.todo.api.model.NewTodoModel;
import hu.app.todo.api.model.TodoModel;
import hu.app.todo.api.model.TodoStatusModel;
import hu.app.todo.todo.TodoStatus;
import hu.app.todo.todo.data.TestTodoFactory;
import hu.app.todo.todo.entity.Todo;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TodoMapperTest {

  private TodoMapper todoMapper;

  @BeforeEach
  void init() {
    todoMapper = new TodoMapper();
  }

  @Test
  @DisplayName("Test new model to entity mapping")
  void testNewModelToEntityMapping() {
    // Given
    NewTodoModel newTodoModel = TestTodoFactory.getNewModel();

    // When
    Todo result = todoMapper.newModelToEntity(newTodoModel);

    // Then
    BDDAssertions.then(result)
        .extracting(Todo::summary, Todo::details, Todo::status)
        .containsExactly(newTodoModel.getSummary(), newTodoModel.getDetails(), TodoStatus.CREATED);
  }

  @Test
  @DisplayName("Test entity to todo model mapping")
  void testEntityToTodoModelMapping() {
    // Given
    Todo entity = TestTodoFactory.getTodoEntity();

    // When
    TodoModel result = todoMapper.entityToModel(entity);

    // Then
    BDDAssertions.then(result)
        .extracting(
            TodoModel::getId,
            TodoModel::getUpdateTime,
            TodoModel::getCreateDate,
            TodoModel::getSummary,
            TodoModel::getDetails,
            TodoModel::getStatus)
        .containsExactly(
            entity.getId(),
            entity.getUpdateTime(),
            entity.getCreateDate(),
            entity.summary(),
            entity.details(),
            TodoStatusModel.valueOf(entity.status().name()));
  }
}
