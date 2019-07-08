package com.example.aplikacjaObecnosc.Admin;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.TextView;

import com.example.aplikacjaObecnosc.Admin.AdminActivity;
import com.example.aplikacjaObecnosc.ServiceClient;
import com.example.aplikacjaObecnosc.Uzytkownicy;
import com.example.aplikacjaObecnosc.ZalogowanyUzytkownik;
import com.microsoft.windowsazure.mobileservices.MobileServiceException;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class AdminLogowanie extends AsyncTask<String,Integer,String>{

    private Context context;
    // private TextView debug;

    /*
       Komunikuje proces logowania
     */
    private ProgressDialog progressDialog;

    /*
      Referencja do tabeli pozwalająca na dostęp oraz modyfikacje
     */
    private MobileServiceTable<Uzytkownicy> mUzytkownicyTable;
    /*
      Lista uzytkownikow zwracanych po zapytaniu do bazy danych
     */
    private List<Uzytkownicy> listaUzytkownikow;
    /*
        Przechowuje informacje o zalogowanym użytkowniku
     */
    private Activity activity;
    private Intent intentMenu;
    private  List<Uzytkownicy> zalogowany;
    private  Uzytkownicy tmp;




    /**
     * Konstruktor logowanie
     * @param context
     */
    public AdminLogowanie(Context context,  Activity activity)
    {
        this.context = context;
        //this.debug = debug;
        this.activity=activity;
        mUzytkownicyTable= ServiceClient.getmInstance().getClient().getTable(Uzytkownicy.class);

    }

    /**
     *Instrukcje  wykonujące sie przed własciwymi instrukcjami logowania
     */
    @Override
    protected void onPreExecute() {
        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("AdminLogowanie");
        progressDialog.show();
    }

    /**
     * Metoda logowania działająca w tle
     * @param strings
     * @return
     */

    @Override
    protected String doInBackground(String... strings) {


        try {
            // Thread.sleep(1000);
            zalogowany=zwrocDaneLogowania(strings[0], strings[1]);
            ZalogowanyUzytkownik.inicjalizacja(zalogowany.get(0));


        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (MobileServiceException e) {
            e.printStackTrace();
        }
        progressDialog.cancel();
        intentMenu = new Intent(context, AdminActivity.class);
        activity.startActivity(intentMenu);

        return null;
    }


   /* @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        progressDialog.cancel();
        //        if(!zalogowany.isEmpty()){

//            tmp = (Uzytkownicy) zalogowany;

        // Thread.sleep(1000);
        ZalogowanyUzytkownik.inicjalizacja(zalogowany.get(0));

        intentMenu = new Intent(context, AdminActivity.class);
        activity.startActivity(intentMenu);
*/





    /**
     * Zwraca zapytanie o
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     * @throws MobileServiceException
     */
    private List<Uzytkownicy> zwrocDaneLogowania(String login,String haslo) throws ExecutionException, InterruptedException, MobileServiceException {

        return mUzytkownicyTable.where().field("login").eq().val(login).and().field("haslo").eq().val(haslo).execute().get();
    }
}

