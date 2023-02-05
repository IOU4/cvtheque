package ma.cvtheque.soft_skills;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import ma.cvtheque.student.StudentDTO;

@Getter
@Setter
public class SoftSkillsDTO {

  private Long id;

  @Size(max = 255)
  private String name;

  private StudentDTO student;

}
