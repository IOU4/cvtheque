package ma.cvtheque.hard_skills;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import ma.cvtheque.util.NotFoundException;

@Service
public class HardSkillsService {

  private final HardSkillsRepository hardSkillsRepository;

  public HardSkillsService(final HardSkillsRepository hardSkillsRepository) {
    this.hardSkillsRepository = hardSkillsRepository;
  }

  public List<HardSkillsDTO> findAll() {
    final List<HardSkills> hardSkillss = hardSkillsRepository.findAll(Sort.by("id"));
    return hardSkillss.stream()
        .map((hardSkills) -> mapToDTO(hardSkills, new HardSkillsDTO()))
        .collect(Collectors.toList());
  }

  public HardSkillsDTO get(final Long id) {
    return hardSkillsRepository.findById(id)
        .map(hardSkills -> mapToDTO(hardSkills, new HardSkillsDTO()))
        .orElseThrow(NotFoundException::new);
  }

  public Long create(final HardSkillsDTO hardSkillsDTO) {
    final HardSkills hardSkills = new HardSkills();
    mapToEntity(hardSkillsDTO, hardSkills);
    return hardSkillsRepository.save(hardSkills).getId();
  }

  public void update(final Long id, final HardSkillsDTO hardSkillsDTO) {
    final HardSkills hardSkills = hardSkillsRepository.findById(id)
        .orElseThrow(NotFoundException::new);
    mapToEntity(hardSkillsDTO, hardSkills);
    hardSkillsRepository.save(hardSkills);
  }

  public void delete(final Long id) {
    hardSkillsRepository.deleteById(id);
  }

  private HardSkillsDTO mapToDTO(final HardSkills hardSkills, final HardSkillsDTO hardSkillsDTO) {
    hardSkillsDTO.setId(hardSkills.getId());
    hardSkillsDTO.setName(hardSkills.getName());
    return hardSkillsDTO;
  }

  private HardSkills mapToEntity(final HardSkillsDTO hardSkillsDTO, final HardSkills hardSkills) {
    hardSkills.setName(hardSkillsDTO.getName());
    return hardSkills;
  }

}
