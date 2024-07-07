package hu.app.todo.todo.service;

import static org.mockito.BDDMockito.given;

import hu.app.todo.api.model.NewTodoModel;
import hu.app.todo.todo.data.TestTodoFactory;
import hu.app.todo.todo.entity.Todo;
import hu.app.todo.todo.mapper.TodoMapper;
import hu.app.todo.todo.persistence.TodoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TodoServiceTest {

  @Mock private TodoRepository todoRepository;
  @Mock private TodoMapper todoMapper;
  @InjectMocks private TodoService todoService;

  @Test
  @DisplayName("Test todo creation")
  void testTodoCreation() {
    // Given
    NewTodoModel newTodoModel = TestTodoFactory.getNewModel();
    Todo newTodoEntity = TestTodoFactory.getTodoEntity();

    given(todoMapper.newModelToEntity(newTodoModel)).willReturn(newTodoEntity);
    given(todoRepository.save(newTodoEntity)).willReturn(newTodoEntity);

    // When
    todoService.createTodo(newTodoModel);

    // Then
    BDDMockito.then(todoMapper).should().newModelToEntity(newTodoModel);
    BDDMockito.then(todoRepository).should().save(newTodoEntity);
  }
}
