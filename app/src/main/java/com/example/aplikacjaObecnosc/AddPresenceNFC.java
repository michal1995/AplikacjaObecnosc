package com.example.aplikacjaObecnosc;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.example.aplikacjaObecnosc.Admin.Studenci;
import com.example.aplikacjaObecnosc.Admin.Zajecia;
import com.example.aplikacjaObecnosc.Student.Grupa;
import com.microsoft.windowsazure.mobileservices.MobileServiceException;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;

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
    NfcModule activity;

    public AddPresenceNFC(NfcModule activity, Context context)
    {
        this.activity= activity;
        progressDialog = new ProgressDialog(context);
        // textZajetyLogin = activity.findViewById(R.id.tvZajetyStudent);
    }

    @Override
    protected void onPreExecute() {
        progressDialog.setTitle("Trwa dodawanie obecności...");
        progressDialog.show();
    }

    private List<Studenci> sprawdzTagStudent(Studenci uzytkownik) throws ExecutionException, InterruptedException {
        String student = uzytkownik.getstudentTag();
        return  mStudentTable.where().field("studentTag").eq().val(uzytkownik.getstudentTag()).execute().get();
    }

    @Override
    protected String doInBackground(final String... strings) {

        new Thread(new Runnable() {

            public void run() {
                try {
                    mStudenci = new Studenci();
                    mStudenci.setstudentTag(String.valueOf(strings[0]));
                    listaStudenci =sprawdzTagStudent(mStudenci);

                    mGrupa = new Grupa();
                    mGrupa.setStudentId(listaStudenci.get(0).getId());
                    mGrupa.setObecnosc(true);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                    return;
                } catch (InterruptedException e) {
                    e.printStackTrace();}


            }
        }).start();
        //textZajetyLogin.setVisibility(View.INVISIBLE);
        return null;
    }




    @Override
    protected void onPostExecute(String s) {
        try {
            mGrupaTable.insert(mGrupa).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
            return;
        } catch (InterruptedException e) {
            e.printStackTrace();}

        this.progressDialog.cancel();
    }


}
