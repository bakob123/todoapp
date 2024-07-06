package hu.app.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
public class MockMvcTest extends IntegrationTest {

  @Autowired protected MockMvc mockMvc;
}
