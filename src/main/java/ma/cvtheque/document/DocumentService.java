package ma.cvtheque.document;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ma.cvtheque.util.NotFoundException;

@Service
@RequiredArgsConstructor
public class DocumentService {

  private final DocumentRepository documentRepository;

  public List<DocumentDTO> findAll() {
    final List<Document> documents = documentRepository.findAll(Sort.by("id"));
    return documents.stream()
        .map((document) -> mapToDTO(document, new DocumentDTO()))
        .collect(Collectors.toList());
  }

  public DocumentDTO get(final Long id) {
    return documentRepository.findById(id)
        .map(document -> mapToDTO(document, new DocumentDTO()))
        .orElseThrow(NotFoundException::new);
  }

  public Long create(final DocumentDTO documentDTO) {
    final Document document = new Document();
    mapToEntity(documentDTO, document);
    return documentRepository.save(document).getId();
  }

  public void update(final Long id, final DocumentDTO documentDTO) {
    final Document document = documentRepository.findById(id)
        .orElseThrow(NotFoundException::new);
    mapToEntity(documentDTO, document);
    documentRepository.save(document);
  }

  public void delete(final Long id) {
    documentRepository.deleteById(id);
  }

  private DocumentDTO mapToDTO(final Document document, final DocumentDTO documentDTO) {
    documentDTO.setId(document.getId());
    documentDTO.setFile(document.getFile());
    documentDTO.setType(document.getType());
    return documentDTO;
  }

  private Document mapToEntity(final DocumentDTO documentDTO, final Document document) {
    document.setFile(documentDTO.getFile());
    document.setType(documentDTO.getType());
    return document;
  }
}
