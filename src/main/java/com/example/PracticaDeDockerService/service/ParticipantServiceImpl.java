package com.example.PracticaDeDockerService.service;

import com.example.PracticaDeDockerService.exception.ResourceExistsException;
import com.example.PracticaDeDockerService.exception.ResourceLengthException;
import com.example.PracticaDeDockerService.exception.ResourceNotFoundException;
import com.example.PracticaDeDockerService.model.LoginDto;
import com.example.PracticaDeDockerService.model.Participant;
import com.example.PracticaDeDockerService.model.Response;
import com.example.PracticaDeDockerService.model.dto.ResponseDto;
import com.example.PracticaDeDockerService.repository.ParticipantRepository;
import com.example.PracticaDeDockerService.repository.ResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ParticipantServiceImpl implements IParticipantService {

  // Required dependencies
  private final ParticipantRepository participantRepository;
  private final ResponseRepository responseRepository;

  // Constructor-based dependency injection
  @Autowired
  public ParticipantServiceImpl(
          ParticipantRepository participantRepository,
          ResponseRepository responseRepository
  ) {
    this.participantRepository = participantRepository;
    this.responseRepository = responseRepository;
  }

  @Override
  public Participant login(LoginDto loginRequest) {
    Participant currentParticipant = participantRepository.findParticipantByUsernameAndPassword(loginRequest.username, loginRequest.password);

    if (currentParticipant == null)
      throw new ResourceNotFoundException();

    return currentParticipant;
  }

  @Override
  public Participant create(Participant participant) {
    Participant currentParticipant = participantRepository.findParticipantByUsername(participant.getUsername());

    if (currentParticipant != null)
      throw new ResourceExistsException();

    participant.setAccountType(Participant.AccountType.PARTICIPANT);
    return participantRepository.save(participant);
  }

  @Override
  public Participant saveResponse(ResponseDto responseDto) {
    Optional<Participant> currentParticipant = participantRepository.findById(responseDto.getParticipantId());

    if (!currentParticipant.isPresent())
      throw new ResourceNotFoundException();

    if (responseDto.getResponseList().size() != 4) {
      throw new ResourceLengthException();
    }

    List<Response> responseList = new ArrayList<>();
    for (Response res : responseDto.getResponseList()) {
      responseList.add(
              new Response(res.getNumber(), res.getRating(), currentParticipant.get())
      );
    }

    responseRepository.saveAll(responseList);
    return participantRepository.findById(currentParticipant.get().getId()).get();
  }

  @Override
  public List<Participant> findAll() {
    return participantRepository.findAll();
  }
}
