package com.example.aplikacjaObecnosc.Student;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.aplikacjaObecnosc.Admin.Studenci;
import com.example.aplikacjaObecnosc.Admin.ZmienHasloActivity;
import com.example.aplikacjaObecnosc.Admin.ZmienHasloAdapter;
import com.example.aplikacjaObecnosc.R;
import com.example.aplikacjaObecnosc.ServiceClient;
import com.example.aplikacjaObecnosc.ZalogowanyStudent;
import com.microsoft.windowsazure.mobileservices.MobileServiceException;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class ZmienSwojeHasloActivity extends AppCompatActivity {
List<Studenci> listaStudentoww;
Studenci student;
MobileServiceTable<Studenci> mStudenci = ServiceClient.getmInstance().getClient().getTable(Studenci.class);
    EditText etSwojeHaslo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zmien_swoje_haslo);
        Button bZmienSwojeHaslo = findViewById(R.id.bZmienSwojeHaslo);

        TextView swojeImie = findViewById(R.id.tvSwojeImie);
        swojeImie.setText(ZalogowanyStudent.getInstance().getZalogowanyUzytkownik().getaImie());
        TextView swojeNazwisko = findViewById(R.id.tvSwojeNazwisko);
        swojeNazwisko.setText(ZalogowanyStudent.getInstance().getZalogowanyUzytkownik().getaNazwisko());
        final EditText etSwojeHaslo = findViewById(R.id.etSwojeHaslo);
        etSwojeHaslo.setText(ZalogowanyStudent.getInstance().getZalogowanyUzytkownik().getaHaslo());
        bZmienSwojeHaslo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AsyncTask<String, String, String>() {


                    @SuppressLint("WrongThread")
                    @Override
                    protected String doInBackground(String... params) {
                        try {
                           // listaStudentoww = mStudenci.where().field("nrIndeksu").eq().val(nrIndeksu).execute().get();
                          //  studenci = listaStudentoww.get(0);
                            student = ZalogowanyStudent.getInstance().getZalogowanyUzytkownik();
                            student.setaHaslo(String.valueOf(etSwojeHaslo.getText()));
                            mStudenci.update(student).get();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        }

                        //listaStudentoww.add(position,article);



                        return null;
                    }
                }.execute();

                // usuwamy element ze źródła danych
                //lista.remove(positionToDelete);
                // poniższa metoda w animowany sposób usunie element z listy
                //notifyItemRemoved(positionToDelete);

            }
        });
    }

}
