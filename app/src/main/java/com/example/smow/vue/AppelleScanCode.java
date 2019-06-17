package com.example.smow.vue;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.smow.controleur.Controle;
import com.example.smow.modele.Utilisateur;
import com.example.smow.outils.ScanCodeActivity;

public class AppelleScanCode extends AppCompatActivity {

    private String codeBarre;
    public static Utilisateur utilisateurChoisi;
    private Controle controle;

    public AppelleScanCode() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.controle = Controle.getInstance(null);
        //setContentView(R.layout.activity_appelle_scan_code);
        ajoutMatUtiliAdapter();
    }

    public void ajoutMatUtiliAdapter(){
        Intent intent = new Intent(this, ScanCodeActivity.class);
        //intent.setClass(this, ScanCodeActivity.class);
        try {
            //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivityForResult(intent,1);
        }
        catch (Exception e) {
            System.out.println("*********************************************************************************************" + e);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                codeBarre=data.getStringExtra("codeBarre");
                controle.ajoutMateriel(utilisateurChoisi,codeBarre);
                Intent intent = new Intent(this, UtilisateurActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        }
    }

    public static void setUtilisateurChoisi(Utilisateur utilisateurChoisi) {
        AppelleScanCode.utilisateurChoisi = utilisateurChoisi;
    }
}
