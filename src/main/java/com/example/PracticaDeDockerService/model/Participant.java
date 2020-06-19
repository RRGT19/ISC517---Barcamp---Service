package com.example.PracticaDeDockerService.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "participants")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "participant_id"))
})
public class Participant extends BaseEntity implements Serializable {

  public enum AccountType {
    OWNER,
    PARTICIPANT,
  }

  @Enumerated(EnumType.STRING)
  private AccountType accountType;

  @NotEmpty(message = "*Please provide a username")
  @Column(unique = true)
  private String username;

  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  @Length(min = 3, message = "*Your password must have at least 3 characters")
  @NotEmpty(message = "*Please provide your password")
  private String password;

  @JsonIgnoreProperties("participant")
  @OneToMany(mappedBy = "participant")
  private Set<Response> response;

  public Participant() {
  }

  public Participant(AccountType accountType, @NotEmpty(message = "*Please provide a username") String username, @Length(min = 3, message = "*Your password must have at least 3 characters") @NotEmpty(message = "*Please provide your password") String password, Set<Response> response) {
    this.accountType = accountType;
    this.username = username;
    this.password = password;
    this.response = response;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Set<Response> getResponse() {
    return response;
  }

  public void setResponse(Set<Response> response) {
    this.response = response;
  }

  public AccountType getAccountType() {
    return accountType;
  }

  public void setAccountType(AccountType accountType) {
    this.accountType = accountType;
  }
}
