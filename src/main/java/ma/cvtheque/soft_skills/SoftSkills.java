package ma.cvtheque.soft_skills;

import java.time.OffsetDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import ma.cvtheque.student.Student;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class SoftSkills {

  @Id
  @Column(nullable = false, updatable = false)
  @GeneratedValue
  private Long id;

  @Column
  private String name;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "student_id")
  private Student student;

  @CreatedDate
  @Column(nullable = false, updatable = false)
  private OffsetDateTime dateCreated;

  @LastModifiedDate
  @Column(nullable = false)
  private OffsetDateTime lastUpdated;

}
