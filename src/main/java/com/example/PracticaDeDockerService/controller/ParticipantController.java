package com.example.PracticaDeDockerService.controller;

import com.example.PracticaDeDockerService.model.LoginDto;
import com.example.PracticaDeDockerService.model.Participant;
import com.example.PracticaDeDockerService.model.Response;
import com.example.PracticaDeDockerService.model.dto.ResponseDto;
import com.example.PracticaDeDockerService.service.ParticipantServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * A {@link RestController} that handles all incoming HTTP requests from the client and returns an appropriate response.
 */
@RestController
// Declares that the url for all the apis in this controller will start with "/participants".
@RequestMapping(value = "/participants")
public class ParticipantController {

  // Required dependencies
  private final ParticipantServiceImpl service;

  // Constructor-based dependency injection
  @Autowired
  public ParticipantController(ParticipantServiceImpl service) {
    this.service = service;
  }

  @PostMapping("/login")
  public ResponseEntity<Participant> login(@RequestBody LoginDto loginRequest, HttpServletRequest request) {
    Participant participant = service.login(loginRequest);
    request.getSession().setAttribute("PARTICIPANT", participant);
    request.getSession().setAttribute("SESSION_ID", request.getSession().getId());
    return new ResponseEntity<>(participant, HttpStatus.OK);
  }

  @PostMapping("/create")
  public ResponseEntity<Participant> create(@Valid @RequestBody Participant participant, HttpServletRequest request) {
    Participant newParticipant = service.create(participant);
    request.getSession().setAttribute("PARTICIPANT", newParticipant);
    request.getSession().setAttribute("SESSION_ID", request.getSession().getId());
    return new ResponseEntity<>(newParticipant, HttpStatus.CREATED);
  }

  @PostMapping("/save-response")
  public ResponseEntity<Participant> saveResponse(@Valid @RequestBody ResponseDto response) {
    return new ResponseEntity<>(service.saveResponse(response), HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<List<Participant>> findAll() {
    return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
  }

}
