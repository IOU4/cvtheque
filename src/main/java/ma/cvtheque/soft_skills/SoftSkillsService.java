package ma.cvtheque.soft_skills;

import java.util.List;
import java.util.stream.Collectors;
import ma.cvtheque.student.Student;
import ma.cvtheque.student.StudentRepository;
import ma.cvtheque.util.NotFoundException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class SoftSkillsService {

    private final SoftSkillsRepository softSkillsRepository;
    private final StudentRepository studentRepository;

    public SoftSkillsService(final SoftSkillsRepository softSkillsRepository,
            final StudentRepository studentRepository) {
        this.softSkillsRepository = softSkillsRepository;
        this.studentRepository = studentRepository;
    }

    public List<SoftSkillsDTO> findAll() {
        final List<SoftSkills> softSkillss = softSkillsRepository.findAll(Sort.by("id"));
        return softSkillss.stream()
                .map((softSkills) -> mapToDTO(softSkills, new SoftSkillsDTO()))
                .collect(Collectors.toList());
    }

    public SoftSkillsDTO get(final Long id) {
        return softSkillsRepository.findById(id)
                .map(softSkills -> mapToDTO(softSkills, new SoftSkillsDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final SoftSkillsDTO softSkillsDTO) {
        final SoftSkills softSkills = new SoftSkills();
        mapToEntity(softSkillsDTO, softSkills);
        return softSkillsRepository.save(softSkills).getId();
    }

    public void update(final Long id, final SoftSkillsDTO softSkillsDTO) {
        final SoftSkills softSkills = softSkillsRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(softSkillsDTO, softSkills);
        softSkillsRepository.save(softSkills);
    }

    public void delete(final Long id) {
        softSkillsRepository.deleteById(id);
    }

    private SoftSkillsDTO mapToDTO(final SoftSkills softSkills, final SoftSkillsDTO softSkillsDTO) {
        softSkillsDTO.setId(softSkills.getId());
        softSkillsDTO.setName(softSkills.getName());
        softSkillsDTO.setStudent(softSkills.getStudent() == null ? null : softSkills.getStudent().getId());
        return softSkillsDTO;
    }

    private SoftSkills mapToEntity(final SoftSkillsDTO softSkillsDTO, final SoftSkills softSkills) {
        softSkills.setName(softSkillsDTO.getName());
        final Student student = softSkillsDTO.getStudent() == null ? null : studentRepository.findById(softSkillsDTO.getStudent())
                .orElseThrow(() -> new NotFoundException("student not found"));
        softSkills.setStudent(student);
        return softSkills;
    }

}
