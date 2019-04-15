package com.example.aplikacjaObecnosc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        EditText login =(EditText) findViewById(R.id.etLogin);
        EditText haslo = (EditText) findViewById(R.id.etPassword);

    }
    public void onClickZaloguj(){




    }


}
