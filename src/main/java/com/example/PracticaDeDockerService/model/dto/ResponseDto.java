package com.example.PracticaDeDockerService.model.dto;

import com.example.PracticaDeDockerService.model.Response;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

public class ResponseDto {

  @NotNull(message = "You must provide the participant id.")
  @Positive(message = "The participant id must be a positive value.")
  private final Long participantId;

  @NotNull(message = "Response list cannot be null.")
  @NotEmpty(message = "Response list cannot be empty.")
  private List<Response> responseList;

  public ResponseDto(@Positive Long participantId, @NotNull(message = "Response list cannot be null.") @NotEmpty(message = "Response list cannot be empty.") List<Response> responseList) {
    this.participantId = participantId;
    this.responseList = responseList;
  }

  public Long getParticipantId() {
    return participantId;
  }

  public List<Response> getResponseList() {
    return responseList;
  }

  public void setResponseList(List<Response> responseList) {
    this.responseList = responseList;
  }
}
