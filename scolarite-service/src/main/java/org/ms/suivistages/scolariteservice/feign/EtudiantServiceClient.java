package org.ms.suivistages.scolariteservice.feign;

import java.util.List;

import org.ms.suivistages.scolariteservice.model.Etudiant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "etudiant-service")
public interface EtudiantServiceClient {
    @GetMapping("/api/etudiants")
    List<Etudiant> findAllEtudiants();
}
