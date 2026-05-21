package org.ms.suivistages.etudiantservice.web;

import java.util.List;
import java.util.Map;

import org.ms.suivistages.etudiantservice.entities.Etudiant;
import org.ms.suivistages.etudiantservice.repository.EtudiantRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/etudiants")
public class EtudiantRestController {
    private final EtudiantRepository etudiantRepository;
    private final String adresseUniversitaire;

    public EtudiantRestController(EtudiantRepository etudiantRepository,
                                  @Value("${universite.adresse:Adresse universitaire non configuree}") String adresseUniversitaire) {
        this.etudiantRepository = etudiantRepository;
        this.adresseUniversitaire = adresseUniversitaire;
    }

    @GetMapping
    public List<Etudiant> all() {
        List<Etudiant> etudiants = etudiantRepository.findAll();
        etudiants.forEach(this::ajouterAdresseUniversitaire);
        return etudiants;
    }

    @GetMapping("/{id}")
    public Etudiant one(@PathVariable Long id) {
        Etudiant etudiant = etudiantRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Etudiant introuvable : " + id));
        ajouterAdresseUniversitaire(etudiant);
        return etudiant;
    }

    @GetMapping("/adresse-universitaire")
    public Map<String, String> adresseUniversitaire() {
        return Map.of("adresseUniversitaire", adresseUniversitaire);
    }

    private void ajouterAdresseUniversitaire(Etudiant etudiant) {
        etudiant.setAdresseUniversitaire(adresseUniversitaire);
    }
}
