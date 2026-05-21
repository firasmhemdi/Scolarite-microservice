package org.ms.suivistages.enseignantservice.repository;

import org.ms.suivistages.enseignantservice.entities.Enseignant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "enseignants")
public interface EnseignantRepository extends JpaRepository<Enseignant, Long> {
}
