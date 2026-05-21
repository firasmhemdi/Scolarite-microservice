package org.ms.suivistages.stageservice.repository;

import org.ms.suivistages.stageservice.entities.Stage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "stages")
public interface StageRepository extends JpaRepository<Stage, Long> {
}
