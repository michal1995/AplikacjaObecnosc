package com.example.aplikacjaObecnosc.Admin;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.aplikacjaObecnosc.R;
import com.example.aplikacjaObecnosc.ServiceClient;
import com.microsoft.windowsazure.mobileservices.MobileServiceException;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ZmienHasloActivity extends AppCompatActivity {
public RecyclerView listastudentow;
public ZmienHasloAdapter mmAdapter;

    private List<Studenci> listaStudentoww ;


    public ZmienHaslo zmienHaslo;
    public MobileServiceTable<Studenci> mStudenci = ServiceClient.getmInstance().getClient().getTable(Studenci.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zmien_haslo);

        listastudentow = (RecyclerView) findViewById(R.id.rvListaStudentow);

        //listastudentow.setHasFixedSize(true);
        listastudentow.setLayoutManager(new LinearLayoutManager(this));
        //listastudentow.setItemAnimator(new DefaultItemAnimator());



zmienHaslo = new ZmienHaslo(this);
zmienHaslo.execute(new String[0]);

    }

    public class ZmienHaslo extends AsyncTask<String,String,List<Studenci>> {
        private MobileServiceTable<Studenci> mStudenci = ServiceClient.getmInstance().getClient().getTable(Studenci.class);
        ZmienHasloActivity activity;

        public ZmienHaslo (Activity paramactivity ){

            this.activity=((ZmienHasloActivity) paramactivity);
        }

        public List<Studenci> zwrocStudentow() throws MobileServiceException, ExecutionException,InterruptedException{

            return mStudenci.execute().get();

        }

        protected List<Studenci> doInBackground(String... paramVarArgs) {

            try{
                listaStudentoww = zwrocStudentow();

               //listaStudentoww.addAll(listaStudentoww);
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

        @Override
        protected void onPostExecute(List<Studenci> studenci) {
            mmAdapter = new ZmienHasloAdapter(ZmienHasloActivity.this,listaStudentoww);
listastudentow.setAdapter(mmAdapter);
        }




    }


}
