package ma.cvtheque.resume;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ResumeDTO {

    private Long id;

    @NotNull
    private String file;

}
