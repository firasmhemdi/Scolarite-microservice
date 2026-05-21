package org.ms.suivistages.stageservice;

import org.ms.suivistages.stageservice.entities.Stage;
import org.ms.suivistages.stageservice.repository.StageRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
@EnableFeignClients
public class StageServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(StageServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(StageRepository stageRepository,
                            RepositoryRestConfiguration repositoryRestConfiguration) {
        repositoryRestConfiguration.exposeIdsFor(Stage.class);
        return args -> {
            stageRepository.save(new Stage(null, "STG-001", "Application de suivi des stages", "Sfax", 1L, 1L, null, null));
            stageRepository.save(new Stage(null, "STG-002", "Plateforme de gestion des conventions", "Tunis", 2L, 2L, null, null));
            stageRepository.save(new Stage(null, "STG-003", "Tableau de bord RH", "Sousse", 3L, 3L, null, null));
        };
    }
}
