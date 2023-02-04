package ma.cvtheque.student;

import java.util.List;
import java.util.stream.Collectors;
import ma.cvtheque.resume.Resume;
import ma.cvtheque.resume.ResumeRepository;
import ma.cvtheque.util.NotFoundException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final ResumeRepository resumeRepository;

    public StudentService(final StudentRepository studentRepository,
            final ResumeRepository resumeRepository) {
        this.studentRepository = studentRepository;
        this.resumeRepository = resumeRepository;
    }

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

    public Long create(final StudentDTO studentDTO) {
        final Student student = new Student();
        mapToEntity(studentDTO, student);
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
        studentDTO.setResume(student.getResume() == null ? null : student.getResume().getId());
        return studentDTO;
    }

    private Student mapToEntity(final StudentDTO studentDTO, final Student student) {
        student.setName(studentDTO.getName());
        student.setEmail(studentDTO.getEmail());
        student.setStatus(studentDTO.getStatus());
        final Resume resume = studentDTO.getResume() == null ? null : resumeRepository.findById(studentDTO.getResume())
                .orElseThrow(() -> new NotFoundException("resume not found"));
        student.setResume(resume);
        return student;
    }

    public boolean emailExists(final String email) {
        return studentRepository.existsByEmailIgnoreCase(email);
    }

}
