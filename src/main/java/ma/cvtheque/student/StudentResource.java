package ma.cvtheque.student;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api/students", produces = MediaType.APPLICATION_JSON_VALUE)
public class StudentResource {

  private final StudentService studentService;

  public StudentResource(final StudentService studentService) {
    this.studentService = studentService;
  }

  @GetMapping
  public ResponseEntity<List<StudentDTO>> getAllStudents() {
    return ResponseEntity.ok(studentService.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<StudentDTO> getStudent(@PathVariable final Long id) {
    return ResponseEntity.ok(studentService.get(id));
  }

  @PostMapping
  public ResponseEntity<Long> createStudent(@RequestBody @Valid final StudentCreationDTO studentCreationDTO) {
    return new ResponseEntity<>(studentService.create(studentCreationDTO), HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Void> updateStudent(@PathVariable final Long id,
      @RequestBody @Valid final StudentDTO studentDTO) {
    studentService.update(id, studentDTO);
    return ResponseEntity.ok().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteStudent(@PathVariable final Long id) {
    studentService.delete(id);
    return ResponseEntity.noContent().build();
  }

}
