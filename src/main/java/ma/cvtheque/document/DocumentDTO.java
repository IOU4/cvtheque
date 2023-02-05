package ma.cvtheque.document;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import ma.cvtheque.student.StudentDTO;

@Getter
@Setter
public class DocumentDTO {

  private Long id;

  @NotNull
  private String file;

  @NotNull
  private DocumentType type;

  private StudentDTO student;

}
