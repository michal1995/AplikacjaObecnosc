package com.example.aplikacjaObecnosc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.aplikacjaObecnosc.Admin.Studenci;


import java.util.ArrayList;
import java.util.List;

public class ZalogowanyStudent extends AppCompatActivity
    {
        private static ZalogowanyStudent instance;
        private List<Studenci> listaDodanychUzytkownikow = new ArrayList();
        private Studenci zalogowany;

    public ZalogowanyStudent(Studenci paramStudent)
        {

                    this.zalogowany = paramStudent;


        }

        public static ZalogowanyStudent getInstance ()
        {
            if (instance == null) {
                return null;
            }
            return instance;
        }

        public static void inicjalizacja (Studenci paramStudent)
        {
            instance = new ZalogowanyStudent(paramStudent);
        }

        public void dodajElementDoListy (Studenci paramUzytkownicy)
        {
            instance.listaDodanychUzytkownikow.add(paramUzytkownicy);
        }

        public List<Studenci> getListaDodanychUzytkownikow ()
        {
            return instance.listaDodanychUzytkownikow;
        }

        public Studenci getZalogowanyStudent ()
        {
            return this.zalogowany;
        }

        public void setZalogowany (Studenci u)
        {
            instance.zalogowany = u;
        }

        public void usunElementDoListy (Studenci u)
        {
            instance.listaDodanychUzytkownikow.remove(u);
        }


        public void zerujElemntyListy ()
        {
            instance.listaDodanychUzytkownikow.clear();
        }


    }

