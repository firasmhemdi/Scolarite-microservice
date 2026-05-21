# Projet microservices - Suivi des stages

Ce projet realise l'architecture demandee au tableau pour une procedure de suivi de stages.

## Services

| Service | Port | Role |
| --- | ---: | --- |
| config-service | 5555 | Configuration centralisee depuis le depot Git local |
| eureka-service | 8761 | Registre des microservices |
| gateway-service | 8888 | API Gateway centralisee (Spring Cloud Gateway) |
| admin-service | 6666 | Administration des APIs |
| scolarite-service | 8081 | Facade de gestion des fiches de stage |
| etudiant-service | 8084 | Gestion des etudiants |
| enseignant-service | 8085 | Gestion des enseignants |
| stage-service | 8086 | Gestion des stages et aggregation etudiant/enseignant |

## Depot de configuration

Le depot Git local est cree ici :

```text
C:\Users\firas\cloud-conf-stages
```

La propriete globale demandee par le prof est dans `application.properties` :

```properties
universite.adresse=ISET, Tunisie
```

Elle est recuperee par `etudiant-service` et `scolarite-service`.

## Ordre de demarrage

Depuis chaque dossier de service, lancer :

```powershell
$env:JAVA_HOME="C:\Program Files\Java\jdk-17.0.18"
$env:Path="$env:JAVA_HOME\bin;$env:Path"
mvn spring-boot:run
```

Ordre conseille :

1. `config-service`
2. `eureka-service`
3. `etudiant-service`
4. `enseignant-service`
5. `stage-service`
6. `scolarite-service`
7. `admin-service`
8. `gateway-service`

Pour tout demarrer automatiquement depuis le dossier `suivi-stages` :

```powershell
powershell -ExecutionPolicy Bypass -File .\start-all.ps1
```

Pour tout arreter :

```powershell
powershell -ExecutionPolicy Bypass -File .\stop-all.ps1
```

## URLs utiles

```text
http://localhost:5555/application/master
http://localhost:5555/etudiant-service/master
http://localhost:8761
http://localhost:8084/api/etudiants
http://localhost:8085/api/enseignants
http://localhost:8086/api/stages/full/1
http://localhost:8081/api/scolarites/fiche-stage/1
http://localhost:6666/api/scolarites/fiche-stage/1
http://localhost:8081/

### Via la gateway (port 8888)

```text
http://localhost:8888/ETUDIANT-SERVICE/api/etudiants
http://localhost:8888/ENSEIGNANT-SERVICE/api/enseignants
http://localhost:8888/STAGE-SERVICE/api/stages
http://localhost:8888/SCOLARITE-SERVICE/api/scolarites/fiche-stage/1
```

L'interface web se trouve sur :

```text
http://localhost:8081/
```

Elle permet d'afficher la liste des stages, d'ajouter un stage et de modifier un stage existant.

Exemple de creation d'une fiche de stage :

```http
POST http://localhost:8081/api/scolarites/fiche-stage
Content-Type: application/json

{
  "code": "STG-004",
  "sujet": "Application mobile de suivi de stages",
  "adresse": "Ariana",
  "enseignantId": 1,
  "etudiantId": 2
}
```
