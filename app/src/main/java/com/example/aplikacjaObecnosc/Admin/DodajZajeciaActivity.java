package com.example.aplikacjaObecnosc.Admin;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import java.util.Calendar;
import java.util.List;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.text.format.DateFormat;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TimePicker;


import com.example.aplikacjaObecnosc.R;
import com.example.aplikacjaObecnosc.ZalogowanyUzytkownik;

public class DodajZajeciaActivity extends FragmentActivity {
    static EditText etCzas;
    static EditText etTemat;
    static EditText etLokalizacja;
    static ListView lvListaZajec;
    DodajZajeciaActivity activity;
    public DodajZajeciaAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodaj_zajecia);



        etTemat = findViewById(R.id.etTemat);
        etLokalizacja = findViewById(R.id.etLokalizacja);

        etCzas = findViewById(R.id.etData);
        etCzas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTruitonDatePickerDialog(v);
                showTruitonTimePickerDialog(v);
            }
        });
        ListView lvListaZajec = findViewById(R.id.lvZajecia);
        mAdapter = new DodajZajeciaAdapter(this,R.layout.wiersz_zajecia);
        lvListaZajec.setAdapter(mAdapter);
        DodajZajeciaDoListy dodajZajeciaDoListy = new DodajZajeciaDoListy(this);
        dodajZajeciaDoListy.execute(new String[0]);
    }

    public void showTruitonDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public static class DatePickerFragment extends DialogFragment implements
            DatePickerDialog.OnDateSetListener {


        public Dialog onCreateDialog(Bundle savedInstanceState) {

            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Do something with the date chosen by the user
            etCzas.setText(year + "-" + (month + 1) + "-" + day);
        }
    }

            public void showTruitonTimePickerDialog(View v) {

            DialogFragment newFragment = new TimePickerFragment();
            newFragment.show(getSupportFragmentManager(), "timePicker");
    }
            public static class TimePickerFragment extends DialogFragment implements
                TimePickerDialog.OnTimeSetListener {

            public Dialog onCreateDialog(Bundle savedInstanceState) {
                // Use the current time as the default values for the picker
                final Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);

                return new TimePickerDialog(getActivity(), this, hour, minute, DateFormat.is24HourFormat(getActivity()));
            }

            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                etCzas.setText(etCzas.getText() + "-"+hourOfDay+":"+ minute);

            }

        }

        public void dodajZajecia(View view){
        DodajZajecia dodajZajecia = new DodajZajecia(this,this);
        dodajZajecia.execute(etTemat.getText().toString(),etLokalizacja.getText().toString(),etCzas.getText().toString());
        }

        public void aktualizujListeWybranych(List<Zajecia> zajecia)
        {
        this.mAdapter.addAll(zajecia);
        }

}
