package ma.cvtheque.soft_skills;

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
@RequestMapping(value = "/api/softSkillss", produces = MediaType.APPLICATION_JSON_VALUE)
public class SoftSkillsResource {

  private final SoftSkillsService softSkillsService;

  public SoftSkillsResource(final SoftSkillsService softSkillsService) {
    this.softSkillsService = softSkillsService;
  }

  @GetMapping
  public ResponseEntity<List<SoftSkillsDTO>> getAllSoftSkillss() {
    return ResponseEntity.ok(softSkillsService.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<SoftSkillsDTO> getSoftSkills(@PathVariable final Long id) {
    return ResponseEntity.ok(softSkillsService.get(id));
  }

  @PostMapping
  public ResponseEntity<Long> createSoftSkills(
      @RequestBody @Valid final SoftSkillsDTO softSkillsDTO) {
    return new ResponseEntity<>(softSkillsService.create(softSkillsDTO), HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Void> updateSoftSkills(@PathVariable final Long id,
      @RequestBody @Valid final SoftSkillsDTO softSkillsDTO) {
    softSkillsService.update(id, softSkillsDTO);
    return ResponseEntity.ok().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteSoftSkills(@PathVariable final Long id) {
    softSkillsService.delete(id);
    return ResponseEntity.noContent().build();
  }

}
