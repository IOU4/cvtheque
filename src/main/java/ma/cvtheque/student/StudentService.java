package ma.cvtheque.student;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ma.cvtheque.util.NotFoundException;

@Service
@RequiredArgsConstructor
public class StudentService {

  private final StudentRepository studentRepository;
  private final PasswordEncoder passwordEncoder;
  private final CreationMapper creationMapper;

  public List<StudentDTO> findAll() {
    final List<Student> students = studentRepository.findAll(Sort.by("id"));
    return students.stream()
        .map((student) -> mapToDTO(student, new StudentDTO()))
        .collect(Collectors.toList());
  }

  public StudentDTO get(final Long id) {
    return studentRepository.findById(id)
        .map(student -> mapToDTO(student, new StudentDTO()))
        .orElseThrow(NotFoundException::new);
  }

  public Long create(final StudentCreationDTO studentCreationDTO) {
    final Student student = creationMapper.apply(studentCreationDTO);
    student.setPassword(passwordEncoder.encode(student.getPassword()));
    return studentRepository.save(student).getId();
  }

  public void update(final Long id, final StudentDTO studentDTO) {
    final Student student = studentRepository.findById(id)
        .orElseThrow(NotFoundException::new);
    mapToEntity(studentDTO, student);
    studentRepository.save(student);
  }

  public void delete(final Long id) {
    studentRepository.deleteById(id);
  }

  private StudentDTO mapToDTO(final Student student, final StudentDTO studentDTO) {
    studentDTO.setId(student.getId());
    studentDTO.setName(student.getName());
    studentDTO.setEmail(student.getEmail());
    studentDTO.setStatus(student.getStatus());
    return studentDTO;
  }

  private Student mapToEntity(final StudentDTO studentDTO, final Student student) {
    student.setName(studentDTO.getName());
    student.setEmail(studentDTO.getEmail());
    student.setStatus(studentDTO.getStatus());
    return student;
  }

  public boolean emailExists(final String email) {
    return studentRepository.existsByEmailIgnoreCase(email);
  }

}
