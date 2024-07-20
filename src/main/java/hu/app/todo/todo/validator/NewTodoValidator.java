package hu.app.todo.todo.validator;

import hu.app.todo.api.model.NewTodoModel;
import hu.app.todo.domain.shared.validation.Validator;

public class NewTodoValidator extends Validator<NewTodoModel> {

  @Override
  public void validate(NewTodoModel target) {
    if (target == null) {
      rejectField(NewTodoModel.class.getSimpleName(), "can not be null");
      return;
    }

    if (target.getSummary() == null || target.getSummary().isBlank()) {
      rejectField("summary", "can not be null or empty");
    }
  }
}
