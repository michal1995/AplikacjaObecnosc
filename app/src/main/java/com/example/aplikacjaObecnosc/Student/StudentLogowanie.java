package com.example.aplikacjaObecnosc.Student;



import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aplikacjaObecnosc.Admin.Studenci;
import com.example.aplikacjaObecnosc.LoginActivity;
import com.example.aplikacjaObecnosc.ServiceClient;
import com.example.aplikacjaObecnosc.Student.StudentActivity;
import com.example.aplikacjaObecnosc.Uzytkownicy;
import com.example.aplikacjaObecnosc.ZalogowanyStudent;
import com.example.aplikacjaObecnosc.ZalogowanyUzytkownik;
import com.microsoft.windowsazure.mobileservices.MobileServiceException;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;

import java.security.acl.LastOwnerException;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class StudentLogowanie extends AsyncTask<String,String,String>{

    private Context context;
    private TextView debug;

    /*
       Komunikuje proces logowania
     */
    private ProgressDialog progressDialog;

    /*
      Referencja do tabeli pozwalająca na dostęp oraz modyfikacje
     */
    private MobileServiceTable<Studenci> mStudentTable= ServiceClient.getmInstance().getClient().getTable(Studenci.class);;
    /*
      Lista uzytkownikow zwracanych po zapytaniu do bazy danych
     */
    private List<Studenci> listaStudentow;
    /*
        Przechowuje informacje o zalogowanym użytkowniku
     */
    private Activity activity;
    private Intent intentMenu;

    private LoginActivity activityLogin;
    public List<Studenci> zalogowanyy ;

    /**
     * Konstruktor logowanie
     * @param context
     */
    public StudentLogowanie(Context context,  Activity activity)
    {
        this.context = context;
        //this.debug = debug;
        this.activity=activity;
        //listaStudentow = null;


    }

    /**
     *Instrukcje  wykonujące sie przed własciwymi instrukcjami logowania
     */
    @Override
    protected void onPreExecute() {
        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("Stufdent Logowanie");
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
             zalogowanyy  =zwrocDaneLogowania(strings[0], strings[1]);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //final Studenci tmpa = zalogowanyy.get(0);


        return null;
    }



    /**
     * Instrukcje wykonujace się po wykonaniu logowania
     * @param s
     */

    @Override
    protected void onPostExecute(String s) {
        progressDialog.cancel();

       if(!zalogowanyy.isEmpty()){


             ZalogowanyStudent.inicjalizacja(zalogowanyy.get(0));

            intentMenu = new Intent(context, StudentActivity.class);
            activity.startActivity(intentMenu);

        }
        else{

           //activity.startActivity(new Intent(context,LoginActivity.class));
           Toast.makeText(context,"niepoprawny login lub haslo :)",Toast.LENGTH_LONG).show();
       }

        return ;
    }



    /**
     * Zwraca zapytanie o
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private List<Studenci> zwrocDaneLogowania(String Imie,String Nazwisko) throws ExecutionException, InterruptedException {

        return mStudentTable.where().field("Imie").eq().val(Imie).and().field("Nazwisko").eq().val(Nazwisko).execute().get();
    }
}
