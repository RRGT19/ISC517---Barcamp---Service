package com.example.PracticaDeDockerService.controller;

import com.example.PracticaDeDockerService.model.Response;
import com.example.PracticaDeDockerService.service.ResponseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * A {@link RestController} that handles all incoming HTTP requests from the client and returns an appropriate response.
 */
@RestController
// Declares that the url for all the apis in this controller will start with "/responses".
@RequestMapping(value = "/responses")
public class ResponseController {

  // Required dependencies
  private final ResponseServiceImpl service;

  // Constructor-based dependency injection
  @Autowired
  public ResponseController(ResponseServiceImpl service) {
    this.service = service;
  }

  @GetMapping
  public ResponseEntity<List<Response>> findAll() {
    return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
  }

}
