package com.example.aplikacjaObecnosc.Admin;


import android.app.ProgressDialog;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.example.aplikacjaObecnosc.ServiceClient;
import com.microsoft.windowsazure.mobileservices.MobileServiceException;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;

import java.util.List;
import java.util.concurrent.ExecutionException;

import static android.support.v4.content.ContextCompat.startActivity;


public class DodajZajecia extends AsyncTask<String,Integer,String> {
    private MobileServiceTable<Zajecia> mZajeciaTable;
    private List<Zajecia> listaZajec;
    Zajecia zajecia = new Zajecia();;
    DodajZajeciaActivity activity;
    ProgressDialog progressDialog;

    public DodajZajecia(DodajZajeciaActivity activity, Context context) {
        this.activity = activity;
        mZajeciaTable = ServiceClient.getmInstance().getmZajeciaTable();
        progressDialog = new ProgressDialog(context);

    }


    @Override
    protected String doInBackground(String... strings) {

        zajecia.setTematZajec(strings[0]);
       zajecia.setLokalizacja(strings[1]);
        zajecia.setData(strings[2]);
        return null;
    }

    @Override
    protected void onPostExecute(String s) {


        //super.onPostExecute(s);
        this.progressDialog.cancel();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        //Thread.sleep(1000);
                        dodajZajeciaa(zajecia);
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                        return;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (MobileServiceException e) {
                        e.printStackTrace();


                    }
                }
            }).start();
       // activity.finish();
       activity.recreate();
    }




        private void dodajZajeciaa (Zajecia zajecia) throws
        InterruptedException, ExecutionException, MobileServiceException {

            mZajeciaTable.insert(zajecia).get();


        }

    }
