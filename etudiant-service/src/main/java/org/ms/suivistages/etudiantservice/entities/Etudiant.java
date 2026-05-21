package org.ms.suivistages.etudiantservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;

@Entity
public class Etudiant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    private String niveau;
    @Transient
    private String adresseUniversitaire;

    public Etudiant() {
    }

    public Etudiant(Long id, String nom, String prenom, String niveau, String adresseUniversitaire) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.niveau = niveau;
        this.adresseUniversitaire = adresseUniversitaire;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    public String getAdresseUniversitaire() {
        return adresseUniversitaire;
    }

    public void setAdresseUniversitaire(String adresseUniversitaire) {
        this.adresseUniversitaire = adresseUniversitaire;
    }
}
