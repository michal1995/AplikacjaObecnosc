package com.example.aplikacjaObecnosc.Student;

import android.app.Activity;
import android.os.AsyncTask;

import com.example.aplikacjaObecnosc.Admin.Studenci;
import com.example.aplikacjaObecnosc.ServiceClient;
import com.microsoft.windowsazure.mobileservices.MobileServiceException;
import com.microsoft.windowsazure.mobileservices.MobileServiceList;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class WyswietlGrupeStudentow extends AsyncTask<String,String, List<Studenci>>{
private MobileServiceTable<Studenci> mStudenci = ServiceClient.getmInstance().getClient().getTable(Studenci.class);
MobileServiceTable<Grupa> mGrupaTable = ServiceClient.getmInstance().getClient().getTable(Grupa.class);
        WyswietlGrupeStudentowActivity activity;
private List<Studenci> listaStudentow ;
private List<Grupa> listaGrup;
        Grupa grupa;

public WyswietlGrupeStudentow (Activity paramactivity ){

        this.activity=((WyswietlGrupeStudentowActivity) paramactivity);
        }

public List<Studenci> zwrocStudentow() throws MobileServiceException, ExecutionException,InterruptedException{
       //MobileServiceList<Grupa> agh= mGrupaTable.where().field("StudentId").execute().get();
         //listaGrup = mGrupaTable.where().field("StudentId").execute().get();
        MobileServiceTable<Grupa> mGrupaTable = ServiceClient.getmInstance().getClient().getTable(Grupa.class);
return mStudenci.where().field("id").eq().val(String.valueOf(mGrupaTable.where().field("StudentId"))).execute().get();
       // return mStudenci.execute().get();
        }
/*List<Grupa>zwrocGrupy(Studenci student) throws MobileServiceException, ExecutionException,InterruptedException{

        //return mGrupaTable.where().field("ZajeciaId").eq().val();
}*/
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
