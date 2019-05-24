package com.example.aplikacjaObecnosc.Admin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.aplikacjaObecnosc.Admin.WyswietlStudentow;
import com.example.aplikacjaObecnosc.R;
import com.example.aplikacjaObecnosc.ZalogowanyStudent;
import com.example.aplikacjaObecnosc.ZalogowanyUzytkownik;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;

import java.util.ArrayList;
import java.util.List;

public class WyswietlStudentowActivity extends AppCompatActivity {
public WyswietlStudentowAdapter mAdapter;
//public List<Studenci> listaStudentow;
public ListView listView;
public WyswietlStudentow wyswietlStudentow;
    public List<Studenci> listaStudentow = new ArrayList();
    MobileServiceTable<Studenci> mobileServiceTable;



    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wyswietl_studentow);


            listView = findViewById(R.id.lvStudenci);


            mAdapter = new WyswietlStudentowAdapter(this,R.layout.wiersz_student);

            listView.setAdapter(mAdapter);
            wyswietlStudentow= new WyswietlStudentow(this);

            wyswietlStudentow.execute(new String[0]);


    }

    public void usunDoListyWybranych(Studenci studenci)
    {
        listaStudentow.remove(studenci);
//mobileServiceTable.delete(studenci);
        //ZalogowanyStudent.getInstance().usunElementDoListy(studenci);
        //listaStudentow.remove(studenci);

    }
    public void aktualizujListeWybranych(List<Studenci> student)
    {
        this.mAdapter.addAll(student);
    }

}
