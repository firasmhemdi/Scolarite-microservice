package org.ms.suivistages.scolariteservice.feign;

import java.util.List;

import org.ms.suivistages.scolariteservice.model.Enseignant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "enseignant-service")
public interface EnseignantServiceClient {
    @GetMapping("/api/enseignants")
    List<Enseignant> findAllEnseignants();
}
