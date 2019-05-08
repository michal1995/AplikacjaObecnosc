package com.example.aplikacjaObecnosc.Admin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.aplikacjaObecnosc.R;

public class AdminActivity extends AppCompatActivity {
Activity activity;
Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
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




}
