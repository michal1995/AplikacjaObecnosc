package com.example.aplikacjaObecnosc.Student;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.aplikacjaObecnosc.Admin.Zajecia;
import com.example.aplikacjaObecnosc.R;

import java.util.List;

public class WyswietlZajeciaActivity extends AppCompatActivity {
RecyclerView listaZajec;
WyswietlZajeciaAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wyswietl_zajecia);
        listaZajec = findViewById(R.id.rvZajecia);
        listaZajec.setLayoutManager(new LinearLayoutManager(this));
        WyswietlZajecia wyswietlZajecia = new WyswietlZajecia(this);
        wyswietlZajecia.execute(new String[0]);
    }
    public void AktualizujListe(List<Zajecia> zajecie){
        mAdapter = new WyswietlZajeciaAdapter(this,zajecie);
        listaZajec.setAdapter(mAdapter);
    }
}
