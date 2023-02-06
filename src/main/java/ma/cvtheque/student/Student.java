package ma.cvtheque.student;

import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import ma.cvtheque.document.Document;
import ma.cvtheque.hard_skills.HardSkills;
import ma.cvtheque.soft_skills.SoftSkills;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Student implements UserDetails {

  @Id
  @Column(nullable = false, updatable = false)
  @GeneratedValue
  private Long id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false, unique = true)
  private String email;

  @Column(nullable = false, columnDefinition = "text")
  private String password;

  @Column
  @Enumerated(EnumType.STRING)
  private StudentStatus status;

  @OneToMany(mappedBy = "student")
  private Set<Document> documents;

  @OneToMany(mappedBy = "student")
  private Set<HardSkills> hardSkills;

  @OneToMany(mappedBy = "student")
  private Set<SoftSkills> softSkills;

  @CreatedDate
  @Column(nullable = false, updatable = false)
  private OffsetDateTime dateCreated;

  @LastModifiedDate
  @Column(nullable = false)
  private OffsetDateTime lastUpdated;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority("app"));
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

}
