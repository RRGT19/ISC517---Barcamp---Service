package com.example.PracticaDeDockerService.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * Base class for entities.
 * Used for all other classes that needs to have an ID.
 * The {@link MappedSuperclass} annotation allows an entity to inherit properties from this class.
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(updatable = false, nullable = false)
  private Long id;

  @Column(updatable = false)
  @CreatedDate
  private Date createdAt;

  /**
   * Returns the identifier of the entity.
   *
   * @return the id
   */
  public Long getId() {
    return id;
  }

  public Date getCreatedAt() {
    return createdAt;
  }
}
