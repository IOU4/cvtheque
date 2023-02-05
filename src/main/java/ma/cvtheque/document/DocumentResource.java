package ma.cvtheque.document;

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
@RequestMapping(value = "/api/documents", produces = MediaType.APPLICATION_JSON_VALUE)
public class DocumentResource {

  private final DocumentService documentService;

  public DocumentResource(final DocumentService documentService) {
    this.documentService = documentService;
  }

  @GetMapping
  public ResponseEntity<List<DocumentDTO>> getAllDocuments() {
    return ResponseEntity.ok(documentService.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<DocumentDTO> getDocument(@PathVariable final Long id) {
    return ResponseEntity.ok(documentService.get(id));
  }

  @PostMapping
  public ResponseEntity<Long> createDocument(@RequestBody @Valid final DocumentDTO documentDTO) {
    return new ResponseEntity<>(documentService.create(documentDTO), HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Void> updateDocument(@PathVariable final Long id,
      @RequestBody @Valid final DocumentDTO documentDTO) {
    documentService.update(id, documentDTO);
    return ResponseEntity.ok().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteDocument(@PathVariable final Long id) {
    documentService.delete(id);
    return ResponseEntity.noContent().build();
  }

}
