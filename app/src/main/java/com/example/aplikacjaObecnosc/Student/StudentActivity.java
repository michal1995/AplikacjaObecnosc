package com.example.aplikacjaObecnosc.Student;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.aplikacjaObecnosc.R;
import com.example.aplikacjaObecnosc.ServiceClient;
import com.example.aplikacjaObecnosc.ZalogowanyStudent;

public class StudentActivity extends AppCompatActivity {
    ZalogowanyStudent asd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

       // asd = new ZalogowanyStudent(new Studenci());


        //@SuppressLint("WrongViewCast")
       // TextView zalogowany =findViewById(R.id.tvZalogowanyJako);
        //zalogowany.setText(ZalogowanyStudent.getInstance().getZalogowanyUzytkownik().getaImie());
    }
    public void przejdzdoWyswietlZajecia(View view){
        startActivity(new Intent(this,WyswietlZajeciaActivity.class));
    }
    public void przejdzDoHasla(View view){
        startActivity(new Intent(this,ZmienSwojeHasloActivity.class));

    }
}
