package org.ms.suivistages.stageservice.entities;

import org.ms.suivistages.stageservice.model.Enseignant;
import org.ms.suivistages.stageservice.model.Etudiant;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;

@Entity
public class Stage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String sujet;
    private String adresse;
    private Long enseignantId;
    private Long etudiantId;
    @Transient
    private Enseignant enseignant;
    @Transient
    private Etudiant etudiant;

    public Stage() {
    }

    public Stage(Long id, String code, String sujet, String adresse, Long enseignantId, Long etudiantId,
                 Enseignant enseignant, Etudiant etudiant) {
        this.id = id;
        this.code = code;
        this.sujet = sujet;
        this.adresse = adresse;
        this.enseignantId = enseignantId;
        this.etudiantId = etudiantId;
        this.enseignant = enseignant;
        this.etudiant = etudiant;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Long getEnseignantId() {
        return enseignantId;
    }

    public void setEnseignantId(Long enseignantId) {
        this.enseignantId = enseignantId;
    }

    public Long getEtudiantId() {
        return etudiantId;
    }

    public void setEtudiantId(Long etudiantId) {
        this.etudiantId = etudiantId;
    }

    public Enseignant getEnseignant() {
        return enseignant;
    }

    public void setEnseignant(Enseignant enseignant) {
        this.enseignant = enseignant;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }
}
