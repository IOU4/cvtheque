package ma.cvtheque.soft_skills;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import ma.cvtheque.util.NotFoundException;

@Service
public class SoftSkillsService {

  private final SoftSkillsRepository softSkillsRepository;

  public SoftSkillsService(final SoftSkillsRepository softSkillsRepository) {
    this.softSkillsRepository = softSkillsRepository;
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
    return softSkillsDTO;
  }

  private SoftSkills mapToEntity(final SoftSkillsDTO softSkillsDTO, final SoftSkills softSkills) {
    softSkills.setName(softSkillsDTO.getName());
    return softSkills;
  }

}
