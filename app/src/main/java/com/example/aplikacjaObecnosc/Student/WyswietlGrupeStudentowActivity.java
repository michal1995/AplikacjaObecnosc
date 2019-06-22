package com.example.aplikacjaObecnosc.Student;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.aplikacjaObecnosc.Admin.Studenci;
import com.example.aplikacjaObecnosc.Admin.WyswietlStudentow;
import com.example.aplikacjaObecnosc.R;

import java.util.List;

public class WyswietlGrupeStudentowActivity extends AppCompatActivity {
    ListView listView;
    WyswietlGrupeStudentowAdapter mAdapter;
    WyswietlGrupeStudentow wyswietlGrupeStudentow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wyswietl_grupe_studentow);

        listView = findViewById(R.id.lvGrupaStudentow);

        mAdapter = new WyswietlGrupeStudentowAdapter(this,R.layout.wiersz_grupa_studentow);

        listView.setAdapter(mAdapter);
        wyswietlGrupeStudentow= new WyswietlGrupeStudentow(this);

        wyswietlGrupeStudentow.execute(new String[0]);
    }

    public void aktualizujListeWybranych(List<Studenci> student)
    {
        this.mAdapter.addAll(student);
    }
}
