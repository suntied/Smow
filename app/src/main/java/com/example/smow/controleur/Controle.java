package com.example.smow.controleur;

import android.content.Context;

import com.example.smow.modele.AccesDistant;
import com.example.smow.modele.Utilisateur;

import org.json.JSONArray;

import java.util.ArrayList;

public final class Controle {

    private static Controle instance = null;
    private static Utilisateur utilisateur;
    //private static AccesLocal accesLocal;
    private static AccesDistant accesDistant;
    private static Context contexte;
    private ArrayList<Utilisateur> lesUtilisateurs = new ArrayList<Utilisateur>();

    /**
     * constructeur private
     */
    private Controle(){
        super();
    }

    /**
     * Création de l'instance
     * @return instance
     */
    public static final Controle getInstance(Context contexte) {
        if(contexte!=null){
            Controle.contexte = contexte;
        }
        if(Controle.instance == null){
            Controle.instance = new Controle();
//          accesLocal = new AccesLocal(contexte);
            accesDistant = new AccesDistant();
//          accesDistant.envoi("dernier", new JSONArray());
            accesDistant.envoi("tous", new JSONArray());
//          utilisateur = accesLocal.recupDernier();
        }
        return Controle.instance;
    }

    public ArrayList<Utilisateur> getLesUtilisateurs() {
        return lesUtilisateurs;
    }

    public void setLesUtilisateurs(ArrayList<Utilisateur> lesUtilisateurs) {
        this.lesUtilisateurs = lesUtilisateurs;
    }


    public void creerProfil(Integer id_utilisateur, String nom, String prenom, String service, String mail, Context contexte){
        utilisateur = new Utilisateur(id_utilisateur,nom,prenom,service,mail);
        lesUtilisateurs.add(utilisateur);
        //accesLocal.ajout(utilisateur);
        accesDistant.envoi("enreg", utilisateur.convertToJSONArray());
    }

    /**
     * Ajout d'un materiel pour un utilisateur
     * @param utilisateur
     */
    public void ajoutMateriel(Utilisateur utilisateur, String codeBarre){
        accesDistant.envoi("ajout", utilisateur.convertToJSONArrayAjout(codeBarre));
    }

    public String getNom() {
        if (utilisateur == null) {
            return null;
        } else {
            return utilisateur.getNom();
        }
    }

    public String getPrenom(){
        if(utilisateur == null){
            return null;
        }else{
            return utilisateur.getPrenom();
        }
    }

    public String getService(){
        if(utilisateur == null){
            return null;
        }else{
            return utilisateur.getService();
        }
    }
    public String getMail(){
        if(utilisateur == null){
            return null;
        }else{
            return utilisateur.getMail();
        }
    }


    public void setUtilisateur(Utilisateur utilisateur) {
        Controle.utilisateur = utilisateur;
    }
}

