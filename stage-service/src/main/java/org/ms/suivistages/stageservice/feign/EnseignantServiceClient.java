package org.ms.suivistages.stageservice.feign;

import org.ms.suivistages.stageservice.model.Enseignant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "enseignant-service")
public interface EnseignantServiceClient {
    @GetMapping("/api/enseignants/{id}")
    Enseignant findEnseignantById(@PathVariable Long id);
}
