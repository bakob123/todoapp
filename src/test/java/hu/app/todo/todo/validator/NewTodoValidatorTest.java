package hu.app.todo.todo.validator;

import static org.assertj.core.api.BDDAssertions.catchThrowable;

import hu.app.todo.api.model.NewTodoModel;
import hu.app.todo.domain.shared.exception.ValidationException;
import hu.app.todo.domain.shared.validation.Validation;
import hu.app.todo.todo.data.TestTodoFactory;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NewTodoValidatorTest {

  private NewTodoValidator validator;
  private Validation<NewTodoModel> validation;

  @BeforeEach
  void init() {
    validator = new NewTodoValidator();
    validation = new Validation<>();
  }

  @Test
  @DisplayName("No validation errors are thrown for correct model")
  void noValidationErrorsAreThrownForCorrectModel() {
    // Given
    NewTodoModel correctModel = TestTodoFactory.getNewModel();
    validation.addTarget(correctModel).addValidator(validator).executeValidations();

    // When
    Throwable throwable = catchThrowable(() -> validation.checkErrors());

    // Then
    BDDAssertions.then(throwable).isNull();
  }

  @Test
  @DisplayName("Summary is null throws validation exception")
  void summaryIsNullThrowsValidationException() {
    // Given
    NewTodoModel todoWithoutSummary = TestTodoFactory.getNewModel();
    todoWithoutSummary.setSummary(null);

    validation.addTarget(todoWithoutSummary).addValidator(validator).executeValidations();

    // When
    Throwable throwable = catchThrowable(() -> validation.checkErrors());

    // Then
    BDDAssertions.then(throwable)
        .isInstanceOf(ValidationException.class)
        .hasMessage("[summary]: can not be null or empty");
  }

  @Test
  @DisplayName("Target is null throws validation exception")
  void targetIsNullThrowsValidationException() {
    // Given
    NewTodoModel newTodoModel = null;

    validation.addTarget(newTodoModel).addValidator(validator).executeValidations();

    // When
    Throwable throwable = catchThrowable(() -> validation.checkErrors());

    // Then
    BDDAssertions.then(throwable)
        .isInstanceOf(ValidationException.class)
        .hasMessage("[NewTodoModel]: can not be null");
  }
}
