package ma.cvtheque.document;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DocumentDTO {

  private Long id;

  @NotNull
  private String file;

  @NotNull
  private DocumentType type;

  private Long studentId;

}
