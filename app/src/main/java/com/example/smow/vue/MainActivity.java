package com.example.smow.vue;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.smow.R;
import com.example.smow.controleur.Controle;

public class MainActivity extends AppCompatActivity {

    private Controle controle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        controle = Controle.getInstance(this);
        //ecouteMenu((ImageButton)findViewById(R.id.btnMenuIMG),CalculActivity.class);
        //ecouteMenu((ImageButton)findViewById(R.id.btnMenuStock),StockActivity.class);
        ecouteMenu((ImageButton)findViewById(R.id.btnMenuUtilisateur),UtilisateurActivity.class);

    }

    /**
     * ouvre l'activité correspondante au moment de click
     * @param btn
     * @param classe
     */
    private void ecouteMenu(ImageButton btn, final Class classe){
        btn.setOnClickListener(new ImageButton.OnClickListener() {
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, classe);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
    }

}
