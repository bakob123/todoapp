package hu.app.todo.domain.shared.validation;

import hu.app.todo.domain.shared.exception.ValidationException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Validation<T> {

  private List<Validator<T>> validators;
  private List<Validator.ValidationError> validationErrors;
  private T target;

  public Validation() {
    this.validators = new ArrayList<>();
    this.validationErrors = new ArrayList<>();
  }

  public Validation<T> addTarget(T target) {
    this.target = target;
    return this;
  }

  public Validation<T> addValidator(Validator<T> validator) {
    if (validators == null) {
      validators = new ArrayList<>();
    }

    validators.add(validator);
    return this;
  }

  public Validation<T> executeValidations() {
    this.validators.forEach(validator -> validator.validate(target));
    validationErrors =
        this.validators.stream().map(Validator::getValidationErrors).flatMap(List::stream).toList();
    return this;
  }

  public void checkErrors() {
    if (!this.validationErrors.isEmpty()) {
      String validationError =
          validationErrors.stream()
              .map(Validator.ValidationError::getValidationMessage)
              .collect(Collectors.joining(",\n"));

      throw new ValidationException(validationError);
    }
  }
}
