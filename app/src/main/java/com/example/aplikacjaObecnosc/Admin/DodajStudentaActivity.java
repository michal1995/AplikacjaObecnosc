package com.example.aplikacjaObecnosc.Admin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.aplikacjaObecnosc.R;

public class DodajStudentaActivity extends AppCompatActivity {
    EditText et_Imie;
    EditText et_Nazwisko;
    EditText etEmail;
    EditText etNrIndeksu;
    EditText etHaslo;

    DodajStudenta dodajStudenta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodaj_studenta);
        et_Imie = findViewById(R.id.etImie);
        et_Nazwisko = findViewById(R.id.etNazwisko);
        etEmail = findViewById(R.id.etEmail);
        etNrIndeksu = findViewById(R.id.etNrIndeksu);
        etHaslo = findViewById(R.id.etHaslo);

    }

    public void dodajStudenta(View v) {
        dodajStudenta = new DodajStudenta(this, this);
        dodajStudenta.execute(et_Imie.getText().toString(), et_Nazwisko.getText().toString(),etEmail.getText().toString(),etNrIndeksu.getText().toString(),etHaslo.getText().toString());

    }
}


