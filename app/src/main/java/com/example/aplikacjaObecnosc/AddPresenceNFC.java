package com.example.aplikacjaObecnosc;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.aplikacjaObecnosc.Admin.Studenci;
import com.example.aplikacjaObecnosc.Admin.Zajecia;
import com.example.aplikacjaObecnosc.Student.Grupa;
import com.microsoft.windowsazure.mobileservices.MobileServiceException;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;

import java.io.Console;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class AddPresenceNFC  extends AsyncTask<String,Integer, String> {

    ProgressDialog progressDialog;
    Zajecia mZajecia;
    Studenci mStudenci;
    List<Studenci> listaStudenci;
    private MobileServiceTable<Studenci> mStudentTable =  ServiceClient.getmInstance().getClient().getTable(Studenci.class);
    private MobileServiceTable<Grupa> mGrupaTable = ServiceClient.getmInstance().getClient().getTable(Grupa.class);
    Grupa mGrupa;

    public AddPresenceNFC()
    {
        // textZajetyLogin = activity.findViewById(R.id.tvZajetyStudent);
    }


    @Override
    protected String doInBackground( String... strings) {


        try {
            mStudenci = new Studenci();
            mStudenci.setstudentTag(String.valueOf(strings[0]));
            listaStudenci = sprawdzTagStudent(mStudenci);
            listaStudenci = mStudentTable.where().field("studentTag").eq().val("2B3355E3").execute().get();
            Log.i("lista","student"+ listaStudenci);
            mGrupa = new Grupa();
            mGrupa.setStudentId(listaStudenci.get(0).getId());
            mGrupa.setObecnosc(true);

        } catch (ExecutionException e) {
            e.printStackTrace();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        //textZajetyLogin.setVisibility(View.INVISIBLE);
        return null;
    }



    private List<Studenci> sprawdzTagStudent(Studenci uzytkownik) throws ExecutionException, InterruptedException {
        String student = uzytkownik.getstudentTag();
        Log.i("tag","student" + student);

        return  mStudentTable.where().field("studentTag").eq().val("2B3355E3").execute().get();

    }


    @Override
    protected void onPostExecute(String s) {
        this.progressDialog.cancel();
        new Thread(new Runnable() {

            public void run() {
                try {
                    dodajDoGrupy(mGrupa);
                } catch (ExecutionException e) {
                    e.printStackTrace();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (MobileServiceException e) {
                    e.printStackTrace();
                }


            }
        });
    }

    private void dodajDoGrupy(Grupa grupa) throws ExecutionException, InterruptedException, MobileServiceException {
        mGrupaTable.insert(grupa).get();
    }


}
