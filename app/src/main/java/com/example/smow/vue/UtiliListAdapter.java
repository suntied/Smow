package com.example.smow.vue;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.smow.R;
import com.example.smow.controleur.Controle;
import com.example.smow.modele.Utilisateur;

import java.util.ArrayList;
import java.util.Locale;

public class UtiliListAdapter extends BaseAdapter {

    private ArrayList<Utilisateur> lesUtilisateurs;
    private ArrayList<Utilisateur> lesRecherchesUtilisateurs;
    private LayoutInflater inflater;
    private Controle controle;
    private Context contexte;
    //private AppelleScanCode appelle;


    public UtiliListAdapter(Context contexte, ArrayList<Utilisateur> lesUtilisateurs){
        this.lesUtilisateurs = lesUtilisateurs;
        this.inflater = LayoutInflater.from(contexte);
        this.controle = Controle.getInstance(null);
        this.lesRecherchesUtilisateurs = new ArrayList<>();
        this.lesRecherchesUtilisateurs.addAll(lesUtilisateurs);
        //this.appelle = new AppelleScanCode();
        this.contexte=contexte;
    }


    /**
     * retourne le nombre de lignes
     * @return
     */
    @Override
    public int getCount() {
        return lesUtilisateurs.size();
    }

    /**
     * retourne l'item de la ligne actuelle
     * @param position
     * @return
     */
    @Override
    public Object getItem(int position) {
        return lesUtilisateurs.get(position);
    }

    /**
     * retourne un indice par rapport à la ligne actuelle
     * @param position
     * @return
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * retourne la ligne (view) formaté avec gestion des événements
     * @param position
     * @param convertView
     * @param parent
     * @return
     */

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // déclaration d'un Holder
        ViewHolder holder;
        System.out.println("*******************************Get View");
        // si la ligne n'existe pas encore
        if(convertView == null) {
            holder = new ViewHolder();
            // la ligne est construite avec un formatage (inflater) relié à layout_liste utili
            convertView = inflater.inflate(R.layout.layout_liste_utili, null);
            // chaque propriétés du holder est relié à une propriété graphique
            holder.txtListNom = convertView.findViewById(R.id.txtListNom);
            holder.txtListPrenom = convertView.findViewById(R.id.txtListPrenom);
            holder.txtListService = convertView.findViewById(R.id.txtListService);
            holder.btnListAdd = convertView.findViewById(R.id.btnListAdd);
            // Affecter le holder à la vue
            System.out.println("******************************IF CONVERT VIEW NULL");
            convertView.setTag(holder);
        }else{
            // récupération du holder dans la ligne existante
            holder = (ViewHolder)convertView.getTag();
        }
        // valorisation du contenu du holder (donc de la ligne)
        holder.txtListNom.setText(lesUtilisateurs.get(position).getNom());
        holder.txtListPrenom.setText(lesUtilisateurs.get(position).getPrenom());
        holder.txtListService.setText(lesUtilisateurs.get(position).getService());
        holder.btnListAdd.setTag(position);
        //Click sur la croix pour ajouter l'id sur le materiel

        holder.btnListAdd.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                int position = (int) v.getTag();
                //ajoutMatUtiliAdapter(lesUtilisateurs.get(position));
                //String result=intent.getStringExtra("1");
                //controle.ajoutMateriel(lesUtilisateurs.get(position));
                envoieValeurVersApelleScanCode(lesUtilisateurs.get(position));


            }
        });

        return convertView;
    }
    //envoie l'utilisateur sélectionner et le contexte associé

    public void envoieValeurVersApelleScanCode(Utilisateur utilisateur){
        //appelle.ajoutMatUtiliAdapter(utilisateur, this.contexte);
        AppelleScanCode.setUtilisateurChoisi(utilisateur);
        Intent intent = new Intent(contexte, AppelleScanCode.class);

        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        contexte.startActivity(intent);
    }
    private class ViewHolder{
        ImageButton btnListAdd;
        TextView txtListNom;
        TextView txtListPrenom;
        TextView txtListService;
    }

    //filter
    public void filter(String charText){
        charText = charText.toLowerCase(Locale.getDefault());
        lesUtilisateurs.clear();
        if (charText.length()==0){
            lesUtilisateurs.addAll(lesRecherchesUtilisateurs);
        }else{
            for (Utilisateur Utilisateur : lesRecherchesUtilisateurs){
                if (Utilisateur.getNom().toLowerCase(Locale.getDefault())
                        .contains(charText)){
                    lesUtilisateurs.add(Utilisateur);
                }
            }
        }
        notifyDataSetChanged();
    }
}
