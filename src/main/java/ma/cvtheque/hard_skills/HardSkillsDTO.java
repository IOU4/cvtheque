package ma.cvtheque.hard_skills;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HardSkillsDTO {

  private Long id;

  @Size(max = 255)
  private String name;

  private Long studentId;

}
