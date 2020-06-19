package com.example.PracticaDeDockerService.service;

import com.example.PracticaDeDockerService.model.Response;

import java.util.List;

public interface IResponseService {
  List<Response> findAll();
}
