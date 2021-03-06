package com.example.aplikacjaObecnosc.Admin;

import android.app.Activity;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.util.Log;

import com.example.aplikacjaObecnosc.ServiceClient;
import com.example.aplikacjaObecnosc.ZalogowanyUzytkownik;
import com.microsoft.windowsazure.mobileservices.MobileServiceException;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class DodajZajeciaDoListy extends AsyncTask<String,String, List<Zajecia>> {
    private MobileServiceTable<Zajecia> mStudenci = ServiceClient.getmInstance().getClient().getTable(Zajecia.class);
    DodajZajeciaActivity activity;
    private List<Zajecia> listaZajec;
    private String idTeacher = ZalogowanyUzytkownik.getInstance().getUserId();

    void dodajZajeciaActivity(List<Zajecia> zajecia){

        activity.aktualizujListeWybranych(zajecia);

    }



    public DodajZajeciaDoListy (Activity paramactivity){
        this.activity=((DodajZajeciaActivity) paramactivity);
    }

    public List<Zajecia> zwrocZajecia() throws MobileServiceException, ExecutionException,InterruptedException{

        return mStudenci.where().field("idTeacher").eq().val(idTeacher).execute().get();
    }


    @Override
    protected List<Zajecia> doInBackground(String... strings) {
        try {
            this.listaZajec=zwrocZajecia();

        } catch (MobileServiceException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected void onPostExecute(List<Zajecia> paramList)
    {
        //Log.i("asdas",":" + ZalogowanyUzytkownik.getInstance().getUserId());
        dodajZajeciaActivity(listaZajec);

    }


}
