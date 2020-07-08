package com.vrcorp.mobilelearningfisika_gelombangbunyi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {

    CardView petunjuk, kompetensi, peta, materi, latihan, tentang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //KUMPULAN CARD
        petunjuk = findViewById(R.id.petunjuk);
        kompetensi = findViewById(R.id.kompetensi);
        peta= findViewById(R.id.peta_konsep);
        materi = findViewById(R.id.gel_bunyi);
        latihan = findViewById(R.id.latihan_soal);
        tentang = findViewById(R.id.tentang);

        // CARD KLIK
        petunjuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                klik("petunjuk");
            }
        });
        petunjuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                klik("kompetensi");
            }
        });
        petunjuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                klik("peta");
            }
        });
        petunjuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                klik("materi");
            }
        });
        petunjuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                klik("latihan");
            }
        });
        petunjuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                klik("tentang");
            }
        });
    }

    public void klik(String kondisi){
        Class activity = PetunjukActivity.class;
        switch (kondisi){
            case "petunjuk":
                activity = PetunjukActivity.class;
                break;
            case "kompetensi":
                activity = PetunjukActivity.class;
                break;
            case "peta":
                activity = PetunjukActivity.class;
                break;
            case "materi":
                activity = PetunjukActivity.class;
                break;
            case "latihan":
                activity = PetunjukActivity.class;
                break;
            case "tentang":
                activity = PetunjukActivity.class;
                break;
        }
        Intent intent = new Intent(getApplicationContext(),activity);
        startActivity(intent);
    }
}
