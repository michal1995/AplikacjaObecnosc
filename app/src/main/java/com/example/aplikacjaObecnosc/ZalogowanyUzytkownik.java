package com.example.aplikacjaObecnosc;
import com.example.aplikacjaObecnosc.Admin.Studenci;

import java.util.ArrayList;
import java.util.List;

public class ZalogowanyUzytkownik
{
    private static ZalogowanyUzytkownik instance;
    private List<Uzytkownicy> listaDodanychUzytkownikow = new ArrayList();
    private Uzytkownicy zalogowany;

    private ZalogowanyUzytkownik(Uzytkownicy paramUzytkownicy)
    {
        this.zalogowany = paramUzytkownicy;
    }

    public static ZalogowanyUzytkownik getInstance()
    {
        if (instance == null) {
            return null;
        }
        return instance;
    }

    public static void inicjalizacja(Uzytkownicy paramUzytkownicy)
    {
        instance = new ZalogowanyUzytkownik(paramUzytkownicy);
    }

    public void dodajElementDoListy(Uzytkownicy paramUzytkownicy)
    {
        instance.listaDodanychUzytkownikow.add(paramUzytkownicy);
    }

    public List<Uzytkownicy> getListaDodanychUzytkownikow()
    {
        return instance.listaDodanychUzytkownikow;
    }

    public String getUserId(){return this.zalogowany.getmId();}
    public Uzytkownicy getZalogowanyUzytkownik()
    {
        return this.zalogowany;
    }

    public void setZalogowany(Uzytkownicy u)
    {
        instance.zalogowany = u;
    }

    public void usunElementDoListy(Studenci u)
    {
        instance.listaDodanychUzytkownikow.remove(u);
    }


    public void zerujElemntyListy()
    {
        instance.listaDodanychUzytkownikow.clear();
    }


}
