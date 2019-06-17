package com.example.smow.vue;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.smow.R;
import com.example.smow.controleur.Controle;
import com.example.smow.modele.Utilisateur;

import java.util.ArrayList;


public class UtilisateurActivity extends AppCompatActivity {

    private Controle controle;
    public String codeBarre;
    private ListView lstHisto;
    private UtiliListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utilisateur);
        this.controle = Controle.getInstance(this);
        ecouteRetourMenu();
        creerListe();
    }

    private void ecouteRetourMenu(){
        ((ImageButton)findViewById(R.id.btnRetourDeUtili)).setOnClickListener(new ImageButton.OnClickListener() {
            public void onClick(View v){
                Intent intent = new Intent(UtilisateurActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
    }

    /**
     * Créer la liste Adapter
     */
    private void creerListe(){
        ArrayList<Utilisateur> lesUtilisateurs = controle.getLesUtilisateurs();
        if(lesUtilisateurs != null){
            lstHisto = (ListView)findViewById(R.id.lstHisto);
            adapter = new UtiliListAdapter(this, lesUtilisateurs);

            lstHisto.setAdapter(adapter);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        MenuItem myActionMenuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView)myActionMenuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (TextUtils.isEmpty(newText)){
                    adapter.filter("");
                    lstHisto.clearTextFilter();
                }
                else {
                    adapter.filter(newText);
                }
                return true;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id==R.id.action_settings){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
