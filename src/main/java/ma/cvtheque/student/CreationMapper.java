package ma.cvtheque.student;

import java.util.function.Function;

import org.springframework.stereotype.Component;

/**
 * CreationMapper
 */
@Component
public class CreationMapper implements Function<StudentCreationDTO, Student> {

  @Override
  public Student apply(StudentCreationDTO t) {
    var student = new Student();
    student.setName(t.getName());
    student.setEmail(t.getEmail());
    student.setPassword(t.getPassword());
    student.setStatus(StudentStatus.NONE);
    return student;
  }

}
