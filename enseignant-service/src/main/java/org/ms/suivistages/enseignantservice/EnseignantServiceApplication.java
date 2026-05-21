package org.ms.suivistages.enseignantservice;

import org.ms.suivistages.enseignantservice.entities.Enseignant;
import org.ms.suivistages.enseignantservice.repository.EnseignantRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class EnseignantServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(EnseignantServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(EnseignantRepository enseignantRepository,
                            RepositoryRestConfiguration repositoryRestConfiguration) {
        repositoryRestConfiguration.exposeIdsFor(Enseignant.class);
        return args -> {
            enseignantRepository.save(new Enseignant(null, "Zayani", "Mohamed"));
            enseignantRepository.save(new Enseignant(null, "Gharbi", "Sonia"));
            enseignantRepository.save(new Enseignant(null, "Mejri", "Anis"));
        };
    }
}
