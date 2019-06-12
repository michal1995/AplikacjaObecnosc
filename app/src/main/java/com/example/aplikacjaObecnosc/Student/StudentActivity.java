package com.example.aplikacjaObecnosc.Student;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.aplikacjaObecnosc.R;

public class StudentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
    }
    public void przejdzdoWyswietlZajecia(View view){
        startActivity(new Intent(this,WyswietlZajeciaActivity.class));
    }
}
