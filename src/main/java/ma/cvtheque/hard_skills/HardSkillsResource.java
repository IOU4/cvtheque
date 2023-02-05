package ma.cvtheque.hard_skills;

import jakarta.validation.Valid;
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

@RestController
@RequestMapping(value = "/api/hardSkillss", produces = MediaType.APPLICATION_JSON_VALUE)
public class HardSkillsResource {

  private final HardSkillsService hardSkillsService;

  public HardSkillsResource(final HardSkillsService hardSkillsService) {
    this.hardSkillsService = hardSkillsService;
  }

  @GetMapping
  public ResponseEntity<List<HardSkillsDTO>> getAllHardSkillss() {
    return ResponseEntity.ok(hardSkillsService.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<HardSkillsDTO> getHardSkills(@PathVariable final Long id) {
    return ResponseEntity.ok(hardSkillsService.get(id));
  }

  @PostMapping
  public ResponseEntity<Long> createHardSkills(
      @RequestBody @Valid final HardSkillsDTO hardSkillsDTO) {
    return new ResponseEntity<>(hardSkillsService.create(hardSkillsDTO), HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Void> updateHardSkills(@PathVariable final Long id,
      @RequestBody @Valid final HardSkillsDTO hardSkillsDTO) {
    hardSkillsService.update(id, hardSkillsDTO);
    return ResponseEntity.ok().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteHardSkills(@PathVariable final Long id) {
    hardSkillsService.delete(id);
    return ResponseEntity.noContent().build();
  }

}
