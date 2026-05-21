package org.ms.suivistages.etudiantservice.repository;

import org.ms.suivistages.etudiantservice.entities.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "etudiants")
public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {
}
