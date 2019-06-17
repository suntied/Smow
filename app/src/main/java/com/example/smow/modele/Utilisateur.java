package com.example.smow.modele;



import org.json.JSONArray;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Utilisateur implements Serializable {


    //propriétés
    private Integer id_utilisateur;
    private String nom;
    private String prenom;
    private String service;
    private String mail;

    public Utilisateur(Integer id_utilisateur, String nom, String prenom, String service, String mail) {
        this.id_utilisateur = id_utilisateur;
        this.nom = nom;
        this.prenom = prenom;
        this.service = service;
        this.mail = mail;
    }


    /**
     * Convertion du profil au format JSONArray
     *
     * @return
     */
    public JSONArray convertToJSONArray() {
        List laListe = new ArrayList();
        laListe.add(id_utilisateur);
        laListe.add(nom);
        laListe.add(prenom);
        laListe.add(service);
        laListe.add(mail);
        return new JSONArray(laListe);
    }
    public JSONArray convertToJSONArrayAjout(String codeBarre) {
        List laListe = new ArrayList();
        laListe.add(id_utilisateur);
        laListe.add(codeBarre);
        return new JSONArray(laListe);
    }

    public Integer getId_utilisateur() {
        return id_utilisateur;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getService() {
        return service;
    }

    public String getMail() {
        return mail;
    }

}
