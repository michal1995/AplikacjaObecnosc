package com.example.aplikacjaObecnosc.Admin;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aplikacjaObecnosc.R;
import com.example.aplikacjaObecnosc.ServiceClient;
import com.example.aplikacjaObecnosc.Uzytkownicy;
import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.MobileServiceException;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;
import com.google.common.util.concurrent.ListenableFuture;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;
import com.microsoft.windowsazure.mobileservices.table.query.ExecutableQuery;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class DodajStudenta extends AsyncTask<String,Integer, String>{
    private MobileServiceTable<Studenci> mStudentTable;
    private List<Studenci> listaUzytkownikowTenSamLogin;
    Studenci tmpStudent;
    ProgressDialog progressDialog;
    DodajStudentaActivity activity;
    //TextView textZajetyLogin;

    public DodajStudenta(DodajStudentaActivity activity,Context context)
    {
        this.activity= activity;
        mStudentTable= ServiceClient.getmInstance().getmStudentTable();
        progressDialog = new ProgressDialog(context);
      // textZajetyLogin = activity.findViewById(R.id.tvZajetyStudent);
    }
    @Override
    protected String doInBackground(String... strings) {




       try {
           tmpStudent= new Studenci();
           tmpStudent.setaImie(String.valueOf(strings[0]));
           tmpStudent.setaNazwisko(String.valueOf(strings[1]));
           tmpStudent.setaEmail(String.valueOf(strings[2]));
           tmpStudent.setAnrIndeksu(String.valueOf(strings[3]));
           tmpStudent.setaHaslo(String.valueOf(strings[4]));

           listaUzytkownikowTenSamLogin=sprawdzDostepnosc(tmpStudent);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }catch (MobileServiceException e)
       {e.printStackTrace();}
        return null;
    }

    @Override
    protected void onPreExecute() {
        progressDialog.setTitle("Rejestracja..");
        progressDialog.show();
    }

    @Override
    protected void onPostExecute(String s) {
        this.progressDialog.cancel();

        if(this.listaUzytkownikowTenSamLogin.isEmpty()) {
            new Thread(new Runnable() {

                public void run() {
                    try {
                        dodajUzytkownika(tmpStudent);
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                        return;
                    } catch (InterruptedException e) {
                        e.printStackTrace();}
                        catch (MobileServiceException e){
                            e.printStackTrace();
                        }

                }
            }).start();
            //textZajetyLogin.setVisibility(View.INVISIBLE);
            activity.finish();
        }
        else
            //textZajetyLogin.setVisibility(View.VISIBLE);
            Toast.makeText(this.activity,"dsadsa",Toast.LENGTH_LONG);
    }

    /**
     * Metoda dodająca użytkownika do tabeli Uzytkownicy w bazie danych
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     * @throws MobileServiceException
     */
    private void dodajUzytkownika(Studenci student) throws ExecutionException, InterruptedException, MobileServiceException {
        mStudentTable.insert(student).get();
    }
    private List<Studenci> sprawdzDostepnosc(Studenci uzytkownik) throws ExecutionException, InterruptedException,MobileServiceException {
        return  mStudentTable.where().field("nrIndeksu").eq().val(uzytkownik.getAnrIndeksu()).execute().get();
    }
}