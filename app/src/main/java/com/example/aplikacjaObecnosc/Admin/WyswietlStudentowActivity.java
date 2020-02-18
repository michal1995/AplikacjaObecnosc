package com.example.aplikacjaObecnosc.Admin;

//import android.support.v7.app.AppCompatActivity;
import android.app.Activity;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;

import com.applandeo.materialcalendarview.CalendarView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import com.example.aplikacjaObecnosc.R;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static android.support.constraint.Constraints.TAG;

public class WyswietlStudentowActivity extends Activity {
    public DodajZajeciaAdapter mAdapter;
    //public List<Studenci> listaStudentow;
    public ListView listView;
    public WyswietlStudentow wyswietlStudentow;
    public List<Zajecia> listaZajec = new ArrayList();
    MobileServiceTable<Studenci> mobileServiceTable;
    WyswietlStudentowActivity activity;
    private CalendarView mCalendarView;
   // private List<EventDStudenciay> mEventDays = new ArrayList<>();
   List<EventDay> events = new ArrayList<>();
   public List<Zajecia> listaZ = new ArrayList();
    TextView busun;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wyswietl_studentow);

        mCalendarView =  findViewById(R.id.calendarView);

        String date = "11/11/2019";
        String parts[] = date.split("/");

        int day = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int year = Integer.parseInt(parts[2]);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);

        long milliTime = calendar.getTimeInMillis();



        //float scalingFactor = 0.9f; // scale down to half the size
        //        mCalendarView.setScaleX(scalingFactor);
       // mCalendarView.setScaleY(scalingFactor);
        //  mCalendarView.setDate (milliTime);
        //calendarView.setFocusedMonthDateColor(Color.RED);
        //calendarView.setFocusedMonthDateColor(7);


        ListView lvListaZajec = findViewById(R.id.lvZajeciaCalendar);
        mAdapter = new DodajZajeciaAdapter(this,R.layout.wiersz_zajecia,true);

        lvListaZajec.setAdapter(mAdapter);
        DodajZajeciaDoKalendarza dodajZajeciaDoListy = new DodajZajeciaDoKalendarza(this);
        dodajZajeciaDoListy.execute(new String[0]);

        LayoutInflater inflater = getLayoutInflater();
        ViewGroup header = (ViewGroup)inflater.inflate(R.layout.header_lv,lvListaZajec,false);
        lvListaZajec.addHeaderView(header);

        busun= header.findViewById(R.id.tvUsun);
        busun.setText("NFC");

    //busun.setVisibility(View.INVISIBLE);

        mCalendarView.setOnDayClickListener(new OnDayClickListener() {
            @Override
            public void onDayClick(EventDay eventDay) {
                mAdapter.clear();
                listaZ.clear();
                Calendar clickedDayCalendar = eventDay.getCalendar();
                Toast.makeText(getApplicationContext(),clickedDayCalendar.getTime().toString(),Toast.LENGTH_LONG).show();
                eventDay.getCalendar().getTime();
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date dates = null;
                for(int x = 0 ; x< listaZajec.size();x++){

                try {
                    dates = dateFormat.parse(listaZajec.get(x).getData());
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Log.i("dsa","data:" + eventDay.getCalendar().getTime().toString());
                    Log.i("dsa","data" + dates.toString());

                    if(eventDay.getCalendar().getTime().equals(dates)){
                    Log.i("dsa","wpada");
                    Zajecia zajecia = new Zajecia();

                    zajecia.setaId(listaZajec.get(x).getaId());
                    zajecia.setData(dates.toString());
                    zajecia.setLokalizacja(listaZajec.get(x).getLokalizacja());
                    zajecia.setTematZajec(listaZajec.get(x).getTematZajec());

                    listaZ.add(zajecia);

                }

                }

                mAdapter.addAll(listaZ);
                ListView lvListaZajec = findViewById(R.id.lvZajeciaCalendar);

                lvListaZajec.setAdapter(mAdapter);


            }

        });
            /*listView = findViewById(R.id.lvStudenci);


            mAdapter = new WyswietlStudentowAdapter(this,R.layout.wiersz_student,this);

            listView.setAdapter(mAdapter);
            wyswietlStudentow= new WyswietlStudentow(this);

            wyswietlStudentow.execute(new String[0]);
*/

    }

    public void aktualizujListeDoKalendarza(List<Zajecia> zajeciax)
    {
        this.mAdapter.addAll(zajeciax);
        listaZajec  = zajeciax;
        //Log.i(TAG,"dsad" + listaZajec.get(0).getaId());
        calendarEventDays();

    }

    public void calendarEventDays(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());
        Log.i(TAG,"dataListazajec: " + listaZajec.size());
        //Date sd = null;
      //  sd.setTime('2019-11-26'+'19:29:01');
        for(int x = 0 ; x< listaZajec.size();x++){
            Log.i(TAG,"dsad" + listaZajec.get(x).getData());
          //  Date date = simpleDateFormat.parse(listaZajec.get(x).getData());
            // Date ds  = new Date(listaZajec.get(x).getData());
           // Log.i(TAG,"data:" + ds.toString());
           // LocalDate date = LocalDate.parse("2018-04-10T04:00:00.000Z");
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date dates = null;
            try {
                dates = dateFormat.parse(listaZajec.get(x).getData());
                Log.i(TAG,"date:" + dates);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dates);
            events.add(new EventDay(calendar, R.drawable.ic_arrow_left));

            mCalendarView.setEvents(events);


            //DateFormat dateFormat = null;
           // String date = listaZajec.get(x).getData();
          //  s= new SimpleDateFormat(listaZajec.get(x).getData());
           // dateFormat.parse()
            //ContactsContract.CommonDataKinds.Event event = new ContactsContract.CommonDataKinds.Event();
            //List<Event> events = new ArrayList<>();
           // events.add(event);

        }

//        for(int x = 0 ; x< listaZajec.size();x++){
//            Log.v("dsa","dsad" + listaZajec.get(x).getData());
//        }


      // Log.i(TAG,"zajecia" + zajecia.getData());
//        Calendar calendar = Calendar.getInstance();
//        events.add(new EventDay(calendar, R.drawable.sample_icon));
////or
//        events.add(new EventDay(calendar, new Drawable()));
////or if you want to specify event label color
//        events.add(new EventDay(calendar, R.drawable.sample_icon, Color.parseColor("#228B22")));
//
//        CalendarView calendarView = (CalendarView) findViewById(R.id.calendarView);
//        calendarView.setEvents(events);

    }



    /*public void aktualizujListeZajec(List<Studenci> student)
    {
        this.mAdapter.addAll((Zajecia) student);
    }*/

}
