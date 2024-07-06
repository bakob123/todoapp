package hu.app.todo;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import hu.app.todo.api.model.NewTodoModel;
import hu.app.todo.todo.TodoStatus;
import hu.app.todo.todo.persistence.TodoRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

@SpringBootTest
@AutoConfigureMockMvc
public class TodoControllerMockMvcIT extends MockMvcTest {

  @Autowired private TodoRepository todoRepository;

  @BeforeEach
  void init() {
    todoRepository.deleteAll();
  }

  @Test
  @DisplayName("Test todo creation")
  void testTodoCreation() throws Exception {
    // Given
    NewTodoModel newTodo =
        new NewTodoModel().summary("Important task").details("I must finish this");

    // When
    ResultActions response =
        mockMvc.perform(
            post("/todos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(newTodo)));

    // Then
    response.andExpectAll(
        status().isCreated(),
        jsonPath("$.id", Matchers.notNullValue()),
        jsonPath("$.updateTime", Matchers.notNullValue()),
        jsonPath("$.createDate", Matchers.notNullValue()),
        jsonPath("$.summary", is(newTodo.getSummary())),
        jsonPath("$.details", is(newTodo.getDetails())),
        jsonPath("$.status", is(TodoStatus.CREATED.name())));
  }
}
