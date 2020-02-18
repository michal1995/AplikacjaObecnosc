package com.example.aplikacjaObecnosc.Admin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.aplikacjaObecnosc.R;

public class AdminActivity extends AppCompatActivity {
Activity activity;
Context context;
Button idListaStudentow;
Button dodajStudenta;
Button dodajZajecia;
Button zmienHaslo;
private Typeface font;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        idListaStudentow = findViewById(R.id.idListaStudentow);
        dodajStudenta = findViewById(R.id.imageButton3);
        dodajZajecia = findViewById(R.id.imageButton6);
        zmienHaslo = findViewById(R.id.imageButton7);

        font = Typeface.createFromAsset( this.getAssets(), "fontawesome-webfont.ttf");

        idListaStudentow.setTypeface(font);
        dodajStudenta.setTypeface(font);
        dodajZajecia.setTypeface(font);
        zmienHaslo.setTypeface(font);


    }
    public void przejdzListaStudentow(View view) {
        startActivity(new Intent(this, WyswietlStudentowActivity.class));
    }

    public void przejdzDodajStudenta(View view) {
        startActivity(new Intent(this, DodajStudentaActivity.class));
    }
    public void przejdzDodajZajecia(View view){
        startActivity(new Intent(this,DodajZajeciaActivity.class));

        }
        public void przejdzZmienHaslo(View view){
        startActivity(new Intent(this,ZmienHasloActivity.class));
        }




}
