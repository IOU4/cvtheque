package ma.cvtheque.student;

import java.time.OffsetDateTime;
import java.util.Set;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;
import ma.cvtheque.document.Document;
import ma.cvtheque.hard_skills.HardSkills;
import ma.cvtheque.soft_skills.SoftSkills;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Student {

  @Id
  @Column(nullable = false, updatable = false)
  @GeneratedValue
  private Long id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false, unique = true)
  private String email;

  @Column
  private String status;

  @OneToOne
  @JoinColumn(name = "document_id")
  private Document document;

  @OneToMany(mappedBy = "student")
  private Set<HardSkills> studentHardSkillss;

  @OneToMany(mappedBy = "student")
  private Set<SoftSkills> studentSoftSkillss;

  @CreatedDate
  @Column(nullable = false, updatable = false)
  private OffsetDateTime dateCreated;

  @LastModifiedDate
  @Column(nullable = false)
  private OffsetDateTime lastUpdated;

}
