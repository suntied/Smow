package com.example.smow.outils;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class MesOutils {


    /**
     * Conversion chaine sous format Thu Feb 28 12:24:40 GMT 2019 (EEE MMM dd hh:mm:ss 'GMT' yyyy) vers date
     * @param uneDate
     * @return
     */
    public static Date convertStringgToDate(String uneDate){
        return convertStringToDate(uneDate,"EEE MMM dd hh:mm:ss 'GMT' yyyy");
    }

    /**
     * Conversion chaine sous format reçu en paramètre vers date
     * @param uneDate
     * @param formatAttendu
     * @return
     */
    public static Date convertStringToDate(String uneDate, String formatAttendu){
        SimpleDateFormat formatter = new SimpleDateFormat(formatAttendu);
        try {
            Date date = formatter.parse(uneDate);
            return date;
        } catch (ParseException e) {
            Log.d("Erreur", "Parse de la date impossible "+e.toString());
        }
        return null;
    }
    /**
     * Convertion d'une Date en chaîne sous la forme yyyy-MM-dd hh:mm:ss pour du mysql
     * @param uneDate
     * @return
     */
    public static String convertDateToString(Date uneDate){
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return date.format(uneDate);
    }

    /**
     * Retourne un float au format String avec 1 chiffre derrère la virgule
     * @param valeur
     * @return
     */
    public static String formatDecimal(Float valeur){
        return String.format("%.01f", valeur);
    }
}
