package com.example.smow.modele;

import android.util.Log;


import com.example.smow.controleur.Controle;
import com.example.smow.outils.AccesHTTP;
import com.example.smow.outils.AsyncResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AccesDistant implements AsyncResponse {

    //constante
    private static final String SERVERADDR = "http://tonAdresseIPduServeur/serveursupport.php";
    private Controle controle;

    public AccesDistant(){
        controle = Controle.getInstance(null);
    }
    /**
     * retour du serveur distant c'est la méthode du AsynResponse interface
     * @param output
     */
    @Override
    public void processFinish(String output) {
        Log.d("serveur", "*******************"+output);
        // découpage du message reçu avec %
        String[] message = output.split("%");
        // dans message{0] : "enreg", "dernier", "Erreur!" c'est un des 3 msg
        // dans message[1] : reste du message

        // s'il y a 2 cases c'est bon
        if(message.length>1){
            if(message[0].equals("enreg")){
                Log.d("enreg","***********************"+message[1]);
            }else{
                if(message[0].equals("dernier")) {
                    Log.d("dernier","***********************"+message[1]);
                    try {
                        JSONObject info = new JSONObject(message[1]);
                        Integer id_utilisateur = info.getInt("id_utilisateur");
                        String nom = info.getString("nom");
                        String prenom = info.getString("prenom");
                        String service = info.getString("service");
                        String mail = info.getString("mail");


                        Utilisateur utilisateur = new Utilisateur(id_utilisateur, nom, prenom, service, mail);

                    } catch (JSONException e) {
                        Log.d("erreur","conversion JSON IMPOSSIBLE"+e.toString());
                    }
                }else{
                    if(message[0].equals("tous")) {
                        Log.d("tous", "***********************" + message[1]);
                        try {
                            JSONArray jSonInfo = new JSONArray(message[1]);
                            ArrayList<Utilisateur> lesUtilisateurs = new ArrayList<Utilisateur>();
                            for (int i = 0; i<jSonInfo.length(); i++){
                                JSONObject info = new JSONObject(jSonInfo.get(i).toString());
                                Integer id_utilisateur = info.getInt("id_utilisateur");
                                String nom = info.getString("nom");
                                String prenom = info.getString("prenom");
                                String service = info.getString("service");
                                String mail = info.getString("mail");
                                Utilisateur utilisateur = new Utilisateur(id_utilisateur, nom, prenom, service, mail);
                                lesUtilisateurs.add(utilisateur);
                            }
                            controle.setLesUtilisateurs(lesUtilisateurs);
                        } catch (JSONException e) {
                            Log.d("erreur","conversion JSON IMPOSSIBLE"+e.toString());
                        }
                    }else {
                        if(message[0].equals("Erreur !")) {
                            Log.d("Erreur !", "***********************" + message[1]);
                        }
                    }
                }
            }
        }
    }

    //Savoir ce que ça fait revoir tuto
    public void envoi(String operation, JSONArray lesDonneesJSON){
        AccesHTTP accesDonnees = new AccesHTTP();
        // lien de délégation
        accesDonnees.delegate = this;
        // ajout paramètres
        accesDonnees.addParam("operation", operation);
        accesDonnees.addParam("lesdonnees", lesDonneesJSON.toString());
        // appel au serveur
        accesDonnees.execute(SERVERADDR);
    }
}
