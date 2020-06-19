package com.example.PracticaDeDockerService.repository;

import com.example.PracticaDeDockerService.model.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Long> {
  Participant findParticipantByUsername(String username);
  Participant findParticipantByUsernameAndPassword(String username, String password);
}
