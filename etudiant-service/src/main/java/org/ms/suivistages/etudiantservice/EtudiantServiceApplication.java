package org.ms.suivistages.etudiantservice;

import org.ms.suivistages.etudiantservice.entities.Etudiant;
import org.ms.suivistages.etudiantservice.repository.EtudiantRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class EtudiantServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(EtudiantServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(EtudiantRepository etudiantRepository,
                            RepositoryRestConfiguration repositoryRestConfiguration) {
        repositoryRestConfiguration.exposeIdsFor(Etudiant.class);
        return args -> {
            etudiantRepository.save(new Etudiant(null, "Ben Ali", "Ahmed", "L3", null));
            etudiantRepository.save(new Etudiant(null, "Trabelsi", "Mariem", "M1", null));
            etudiantRepository.save(new Etudiant(null, "Mansouri", "Yassine", "M2", null));
        };
    }
}
