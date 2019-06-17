package com.example.smow.outils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScanCodeActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler{
    ZXingScannerView ScannerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScannerView = new ZXingScannerView(this);
        //c'est la vue qu'on envoie du coup on mets scan car on veut avoir la camera
        setContentView(ScannerView);
    }

    @Override
    public void handleResult(Result result) {
        Intent returnIntent = new Intent();
        returnIntent.putExtra("codeBarre",result.getText());
        //UtiliListAdapter.setCodeBarre(result.getText());
        setResult(Activity.RESULT_OK, returnIntent);
        finish();

        onBackPressed();
    }
    @Override
    protected void onResume() {
        super.onResume();
        ScannerView.setResultHandler(this);
        ScannerView.startCamera();
    }

    @Override
    protected void onPause() {
        super.onPause();

        ScannerView.stopCamera();

    }

}
