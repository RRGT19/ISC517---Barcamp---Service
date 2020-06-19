package com.example.PracticaDeDockerService.repository;

import com.example.PracticaDeDockerService.model.Response;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponseRepository extends JpaRepository<Response, Long> {
}
