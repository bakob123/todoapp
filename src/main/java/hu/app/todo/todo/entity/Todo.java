package hu.app.todo.todo.entity;

import hu.app.todo.domain.shared.entity.Auditable;
import hu.app.todo.todo.TodoStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Entity
@Getter
@Setter
@ToString
@Table(name = "todo")
@Accessors(chain = true, fluent = true)
@NoArgsConstructor
public class Todo extends Auditable {

  @Column(name = "summary", nullable = false)
  private String summary;

  @Column(name = "details", nullable = true)
  private String details;

  @Enumerated(EnumType.STRING)
  @Column(name = "status", nullable = false)
  private TodoStatus status;
}
