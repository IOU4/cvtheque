package ma.cvtheque.student;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentDTO {

  private Long id;

  @NotNull
  @Size(max = 255)
  private String name;

  @NotNull
  @Size(max = 255)
  private String email;

  private StudentStatus status;
}
