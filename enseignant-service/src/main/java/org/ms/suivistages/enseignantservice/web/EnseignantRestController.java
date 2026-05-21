package org.ms.suivistages.enseignantservice.web;

import java.util.List;

import org.ms.suivistages.enseignantservice.entities.Enseignant;
import org.ms.suivistages.enseignantservice.repository.EnseignantRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/enseignants")
public class EnseignantRestController {
    private final EnseignantRepository enseignantRepository;

    public EnseignantRestController(EnseignantRepository enseignantRepository) {
        this.enseignantRepository = enseignantRepository;
    }

    @GetMapping
    public List<Enseignant> all() {
        return enseignantRepository.findAll();
    }

    @GetMapping("/{id}")
    public Enseignant one(@PathVariable Long id) {
        return enseignantRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Enseignant introuvable : " + id));
    }
}
