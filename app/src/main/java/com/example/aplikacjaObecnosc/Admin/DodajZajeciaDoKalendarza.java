package com.example.aplikacjaObecnosc.Admin;

import android.app.Activity;
import android.os.AsyncTask;

import com.example.aplikacjaObecnosc.ServiceClient;
import com.microsoft.windowsazure.mobileservices.MobileServiceException;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class DodajZajeciaDoKalendarza extends AsyncTask<String,String, List<Zajecia>> {
    private MobileServiceTable<Zajecia> mZajecia = ServiceClient.getmInstance().getClient().getTable(Zajecia.class);
    DodajZajeciaActivity activity;
    WyswietlStudentowActivity activityStudent;
    private List<Zajecia> listaZajec;
    private List<Zajecia> listaZajec2;



    public DodajZajeciaDoKalendarza (Activity paramactivity ){

       // this.activity=((DodajZajeciaActivity) paramactivity);
        this.activityStudent = ((WyswietlStudentowActivity) paramactivity);
    }

    public List<Zajecia> zwrocZajecia() throws MobileServiceException, ExecutionException,InterruptedException{

        return mZajecia.execute().get();
    }


    @Override
    protected List<Zajecia> doInBackground(String... strings) {
        try {
            this.listaZajec=zwrocZajecia();
            // this.listaZajec2=zwrocZajecia();

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
           // dodajZajeciaActivity(listaZajec);

        activityStudent.aktualizujListeDoKalendarza(listaZajec);
    }


}

