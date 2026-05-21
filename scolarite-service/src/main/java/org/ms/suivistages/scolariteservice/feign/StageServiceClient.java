package org.ms.suivistages.scolariteservice.feign;

import java.util.List;

import org.ms.suivistages.scolariteservice.model.Stage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "stage-service")
public interface StageServiceClient {
    @GetMapping("/api/stages")
    List<Stage> findAllStages();

    @GetMapping("/api/stages/full/{id}")
    Stage findFullStageById(@PathVariable Long id);

    @PostMapping("/api/stages")
    Stage saveStage(@RequestBody Stage stage);

    @PutMapping("/api/stages/{id}")
    Stage updateStage(@PathVariable Long id, @RequestBody Stage stage);
}
