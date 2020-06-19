package com.example.PracticaDeDockerService.service;

import com.example.PracticaDeDockerService.model.LoginDto;
import com.example.PracticaDeDockerService.model.Participant;
import com.example.PracticaDeDockerService.model.Response;
import com.example.PracticaDeDockerService.model.dto.ResponseDto;

import java.util.List;

public interface IParticipantService {
  Participant create(Participant participant);
  List<Participant> findAll();
  Participant saveResponse(ResponseDto response);
  Participant login(LoginDto loginRequest);
}
