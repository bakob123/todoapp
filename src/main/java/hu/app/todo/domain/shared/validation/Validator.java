package hu.app.todo.domain.shared.validation;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NonNull;

public abstract class Validator<T> {

  @Getter private List<ValidationError> validationErrors;

  protected Validator() {
    this.validationErrors = new ArrayList<>();
  }

  public abstract void validate(T target);

  protected void rejectField(String fieldName, String errorMessage) {
    validationErrors.add(new ValidationError(fieldName, errorMessage));
  }

  protected record ValidationError(@NonNull String field, @NonNull String errorMessage) {
    private static final String VALIDATION_ERROR_MESSAGE_TEMPLATE = "[%s]: %s";

    public String getValidationMessage() {
      return VALIDATION_ERROR_MESSAGE_TEMPLATE.formatted(field, errorMessage);
    }
  }
}
