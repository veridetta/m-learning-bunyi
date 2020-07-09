package com.vrcorp.mobilelearningfisika_gelombangbunyi;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    CardView petunjuk, kompetensi, peta, materi, latihan, tentang;
    boolean doubleBackToExitPressedOnce = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
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
        kompetensi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                klik("kompetensi");
            }
        });
        peta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                klik("peta");
            }
        });
        materi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                klik("materi");
            }
        });
        latihan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                klik("latihan");
            }
        });
        tentang.setOnClickListener(new View.OnClickListener() {
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
                activity = KompetensiActivity.class;
                break;
            case "peta":
                activity = PetaKonsepActivity.class;
                break;
            case "materi":
                activity = PetunjukActivity.class;
                break;
            case "latihan":
                activity = PetunjukActivity.class;
                break;
            case "tentang":
                activity = TentangActivity.class;
                break;
        }
        Intent intent = new Intent(getApplicationContext(),activity);
        startActivity(intent);
    }
    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            finish();
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}
