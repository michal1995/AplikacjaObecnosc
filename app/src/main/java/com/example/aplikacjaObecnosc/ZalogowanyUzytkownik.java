package com.example.aplikacjaObecnosc;
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

    public Uzytkownicy getZalogowanyUzytkownik()
    {
        return this.zalogowany;
    }

    public void setZalogowany(Uzytkownicy paramUzytkownicy)
    {
        instance.zalogowany = paramUzytkownicy;
    }

    public void usunElementDoListy(Uzytkownicy paramUzytkownicy)
    {
        instance.listaDodanychUzytkownikow.remove(paramUzytkownicy);
    }

    public void zerujElemntyListy()
    {
        instance.listaDodanychUzytkownikow.clear();
    }
}
