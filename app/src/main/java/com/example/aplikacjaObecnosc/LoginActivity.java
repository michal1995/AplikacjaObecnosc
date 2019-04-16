package com.example.aplikacjaObecnosc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.net.MalformedURLException;

public class LoginActivity extends AppCompatActivity {
    EditText login;
    EditText haslo;
    TextView debug;
    RadioButton admin;
    RadioButton student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        this.login =(EditText) findViewById(R.id.etLogin);
        this.haslo = (EditText) findViewById(R.id.etPassword);
        this.debug = (TextView) findViewById(R.id.debug);
        this.admin = (RadioButton) findViewById(R.id.rbAdmin);
        this.student = (RadioButton) findViewById(R.id.rbStudent);
        try
        {
            ServiceClient.Initialize(this);
            return;
        }
        catch (MalformedURLException paramBundle)
        {
            paramBundle.printStackTrace();
        }
    }

    public void onClickZaloguj(View view) {
        debug.setVisibility(View.INVISIBLE);
        if (admin.isChecked()) {
            AdminLogowanie myTask = new AdminLogowanie(this, this.debug, this);
            myTask.execute(new String[]{this.login.getText().toString(), this.haslo.getText().toString()});
        }
        else
            if(student.isChecked()){
                StudentLogowanie myTask = new StudentLogowanie(this,this.debug,this);
                myTask.execute(new String[]{this.login.getText().toString(),this.haslo.getText().toString()});


            }
    }


    }



