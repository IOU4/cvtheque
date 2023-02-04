package ma.cvtheque.resume;

import java.util.List;
import java.util.stream.Collectors;
import ma.cvtheque.util.NotFoundException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class ResumeService {

    private final ResumeRepository resumeRepository;

    public ResumeService(final ResumeRepository resumeRepository) {
        this.resumeRepository = resumeRepository;
    }

    public List<ResumeDTO> findAll() {
        final List<Resume> resumes = resumeRepository.findAll(Sort.by("id"));
        return resumes.stream()
                .map((resume) -> mapToDTO(resume, new ResumeDTO()))
                .collect(Collectors.toList());
    }

    public ResumeDTO get(final Long id) {
        return resumeRepository.findById(id)
                .map(resume -> mapToDTO(resume, new ResumeDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final ResumeDTO resumeDTO) {
        final Resume resume = new Resume();
        mapToEntity(resumeDTO, resume);
        return resumeRepository.save(resume).getId();
    }

    public void update(final Long id, final ResumeDTO resumeDTO) {
        final Resume resume = resumeRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(resumeDTO, resume);
        resumeRepository.save(resume);
    }

    public void delete(final Long id) {
        resumeRepository.deleteById(id);
    }

    private ResumeDTO mapToDTO(final Resume resume, final ResumeDTO resumeDTO) {
        resumeDTO.setId(resume.getId());
        resumeDTO.setFile(resume.getFile());
        return resumeDTO;
    }

    private Resume mapToEntity(final ResumeDTO resumeDTO, final Resume resume) {
        resume.setFile(resumeDTO.getFile());
        return resume;
    }

}
