package org.ms.suivistages.stageservice.model;

public class Etudiant {
    private Long id;
    private String nom;
    private String prenom;
    private String niveau;
    private String adresseUniversitaire;

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
