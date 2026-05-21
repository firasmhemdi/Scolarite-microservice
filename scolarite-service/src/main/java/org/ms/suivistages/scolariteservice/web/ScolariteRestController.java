package org.ms.suivistages.scolariteservice.web;

import java.util.List;
import java.util.Map;

import org.ms.suivistages.scolariteservice.feign.EnseignantServiceClient;
import org.ms.suivistages.scolariteservice.feign.EtudiantServiceClient;
import org.ms.suivistages.scolariteservice.feign.StageServiceClient;
import org.ms.suivistages.scolariteservice.model.Enseignant;
import org.ms.suivistages.scolariteservice.model.Etudiant;
import org.ms.suivistages.scolariteservice.model.Stage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/scolarites")
public class ScolariteRestController {
    private final StageServiceClient stageServiceClient;
    private final EtudiantServiceClient etudiantServiceClient;
    private final EnseignantServiceClient enseignantServiceClient;
    private final String adresseUniversitaire;

    public ScolariteRestController(StageServiceClient stageServiceClient,
                                   EtudiantServiceClient etudiantServiceClient,
                                   EnseignantServiceClient enseignantServiceClient,
                                   @Value("${universite.adresse:Adresse universitaire non configuree}") String adresseUniversitaire) {
        this.stageServiceClient = stageServiceClient;
        this.etudiantServiceClient = etudiantServiceClient;
        this.enseignantServiceClient = enseignantServiceClient;
        this.adresseUniversitaire = adresseUniversitaire;
    }

    @GetMapping("/stages")
    public List<Stage> stages() {
        return stageServiceClient.findAllStages();
    }

    @GetMapping("/fiche-stage/{id}")
    public Stage ficheStage(@PathVariable Long id) {
        return stageServiceClient.findFullStageById(id);
    }

    @PostMapping("/fiche-stage")
    public Stage creerFicheStage(@RequestBody Stage stage) {
        return stageServiceClient.saveStage(stage);
    }

    @PutMapping("/fiche-stage/{id}")
    public Stage modifierFicheStage(@PathVariable Long id, @RequestBody Stage stage) {
        return stageServiceClient.updateStage(id, stage);
    }

    @GetMapping("/etudiants")
    public List<Etudiant> etudiants() {
        return etudiantServiceClient.findAllEtudiants();
    }

    @GetMapping("/enseignants")
    public List<Enseignant> enseignants() {
        return enseignantServiceClient.findAllEnseignants();
    }

    @GetMapping("/adresse-universitaire")
    public Map<String, String> adresseUniversitaire() {
        return Map.of("adresseUniversitaire", adresseUniversitaire);
    }
}
