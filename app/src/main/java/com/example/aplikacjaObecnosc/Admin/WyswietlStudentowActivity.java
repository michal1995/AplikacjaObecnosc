package com.example.aplikacjaObecnosc.Admin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.aplikacjaObecnosc.Admin.WyswietlStudentow;
import com.example.aplikacjaObecnosc.R;

import java.util.ArrayList;
import java.util.List;

public class WyswietlStudentowActivity extends AppCompatActivity {
public WyswietlStudentowAdapter mAdapter;
public List<Studenci> listaStudentow;
public ListView listView;
public WyswietlStudentow wyswietlStudentow;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wyswietl_studentow);

            listaStudentow = new ArrayList<>();
            listView = findViewById(R.id.lvStudenci);


            mAdapter = new WyswietlStudentowAdapter(this,R.layout.wiersz_student);

            listView.setAdapter(mAdapter);
            wyswietlStudentow= new WyswietlStudentow(this);

            wyswietlStudentow.execute(new String[0]);

    }
    public void aktualizujListeWybranych(List<Studenci> paramList)
    {
        this.mAdapter.addAll(paramList);
    }
}
