package com.example.PracticaDeDockerService.service;

import com.example.PracticaDeDockerService.model.Response;
import com.example.PracticaDeDockerService.repository.ResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResponseServiceImpl implements IResponseService {

  // Required dependencies
  private final ResponseRepository responseRepository;

  // Constructor-based dependency injection
  @Autowired
  public ResponseServiceImpl(ResponseRepository responseRepository) {
    this.responseRepository = responseRepository;
  }

  @Override
  public List<Response> findAll() {
    return responseRepository.findAll();
  }
}
