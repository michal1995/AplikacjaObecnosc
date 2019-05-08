package com.example.aplikacjaObecnosc.Admin;

import android.app.Activity;
import android.os.AsyncTask;
import android.view.View;

import com.example.aplikacjaObecnosc.ServiceClient;
import com.microsoft.windowsazure.mobileservices.MobileServiceException;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class WyswietlStudentow extends AsyncTask<String,String,List<Studenci>> {
    private MobileServiceTable<Studenci> mStudenci = ServiceClient.getmInstance().getClient().getTable(Studenci.class);
    WyswietlStudentowActivity activity;
    private List<Studenci> listaStudentow;

    public WyswietlStudentow (Activity paramactivity ){

this.activity=((WyswietlStudentowActivity) paramactivity);
    }

    public List<Studenci> zwrocStudentow() throws MobileServiceException,ExecutionException,InterruptedException{

        return mStudenci.execute().get();
    }

    protected List<Studenci> doInBackground(String... paramVarArgs) {

        try{
            this.listaStudentow = zwrocStudentow();
        }
        catch (ExecutionException e){

                e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (MobileServiceException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected void onPostExecute(List<Studenci> paramList)
    {
        this.activity.aktualizujListeWybranych(this.listaStudentow);
    }



}
