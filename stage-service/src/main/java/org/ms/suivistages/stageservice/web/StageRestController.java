package org.ms.suivistages.stageservice.web;

import java.util.List;

import org.ms.suivistages.stageservice.entities.Stage;
import org.ms.suivistages.stageservice.feign.EnseignantServiceClient;
import org.ms.suivistages.stageservice.feign.EtudiantServiceClient;
import org.ms.suivistages.stageservice.repository.StageRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stages")
public class StageRestController {
    private final StageRepository stageRepository;
    private final EnseignantServiceClient enseignantServiceClient;
    private final EtudiantServiceClient etudiantServiceClient;

    public StageRestController(StageRepository stageRepository,
                               EnseignantServiceClient enseignantServiceClient,
                               EtudiantServiceClient etudiantServiceClient) {
        this.stageRepository = stageRepository;
        this.enseignantServiceClient = enseignantServiceClient;
        this.etudiantServiceClient = etudiantServiceClient;
    }

    @GetMapping
    public List<Stage> all() {
        return stageRepository.findAll();
    }

    @GetMapping("/{id}")
    public Stage one(@PathVariable Long id) {
        return stageRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Stage introuvable : " + id));
    }

    @GetMapping("/full/{id}")
    public Stage fullStage(@PathVariable Long id) {
        Stage stage = one(id);
        stage.setEnseignant(enseignantServiceClient.findEnseignantById(stage.getEnseignantId()));
        stage.setEtudiant(etudiantServiceClient.findEtudiantById(stage.getEtudiantId()));
        return stage;
    }

    @PostMapping
    public Stage save(@RequestBody Stage stage) {
        stage.setId(null);
        return stageRepository.save(stage);
    }

    @PutMapping("/{id}")
    public Stage update(@PathVariable Long id, @RequestBody Stage stageRequest) {
        Stage stage = one(id);
        stage.setCode(stageRequest.getCode());
        stage.setSujet(stageRequest.getSujet());
        stage.setAdresse(stageRequest.getAdresse());
        stage.setEnseignantId(stageRequest.getEnseignantId());
        stage.setEtudiantId(stageRequest.getEtudiantId());
        return stageRepository.save(stage);
    }
}
