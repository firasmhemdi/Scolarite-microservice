package org.ms.suivistages.stageservice.feign;

import org.ms.suivistages.stageservice.model.Etudiant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "etudiant-service")
public interface EtudiantServiceClient {
    @GetMapping("/api/etudiants/{id}")
    Etudiant findEtudiantById(@PathVariable Long id);
}
