package ma.cvtheque.resume;

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
@RequestMapping(value = "/api/resumes", produces = MediaType.APPLICATION_JSON_VALUE)
public class ResumeResource {

    private final ResumeService resumeService;

    public ResumeResource(final ResumeService resumeService) {
        this.resumeService = resumeService;
    }

    @GetMapping
    public ResponseEntity<List<ResumeDTO>> getAllResumes() {
        return ResponseEntity.ok(resumeService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResumeDTO> getResume(@PathVariable final Long id) {
        return ResponseEntity.ok(resumeService.get(id));
    }

    @PostMapping
    public ResponseEntity<Long> createResume(@RequestBody @Valid final ResumeDTO resumeDTO) {
        return new ResponseEntity<>(resumeService.create(resumeDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateResume(@PathVariable final Long id,
            @RequestBody @Valid final ResumeDTO resumeDTO) {
        resumeService.update(id, resumeDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResume(@PathVariable final Long id) {
        resumeService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
