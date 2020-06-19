package com.example.PracticaDeDockerService.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Entity
@Table(name = "surveys_response")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "response_id"))
})
public class Response extends BaseEntity implements Serializable {

  @Range(min = 1, max = 5, message = "*The survey number must be between 1-5.")
  @Column(nullable = false, updatable = false)
  private int number;

  @Column(nullable = false, updatable = false)
  private String rating;

  @JsonIgnoreProperties("response")
  @ManyToOne(optional = false)
  private Participant participant;

  public Response() {
  }

  public Response(@Range(min = 1, max = 5, message = "*The survey number must be between 1-5.") int number, @NotEmpty(message = "*Please provide the rating.") String rating, Participant participant) {
    this.number = number;
    this.rating = rating;
    this.participant = participant;
  }

  public int getNumber() {
    return number;
  }

  public void setNumber(int number) {
    this.number = number;
  }

  public String getRating() {
    return rating;
  }

  public void setRating(String rating) {
    this.rating = rating;
  }

  public Participant getParticipant() {
    return participant;
  }

  public void setParticipant(Participant participant) {
    this.participant = participant;
  }
}
