package com.example.aplikacjaObecnosc.Student;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Typeface;
import android.nfc.NfcAdapter;
import android.nfc.tech.IsoDep;
import android.nfc.tech.Ndef;
import android.nfc.tech.NfcA;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aplikacjaObecnosc.Admin.Studenci;
import com.example.aplikacjaObecnosc.NfcModule;
import com.example.aplikacjaObecnosc.R;
import com.example.aplikacjaObecnosc.ServiceClient;
import com.example.aplikacjaObecnosc.ZalogowanyStudent;
import com.example.aplikacjaObecnosc.ZalogowanyUzytkownik;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class StudentActivity extends AppCompatActivity {
    boolean receiveButton =false;
    private NfcAdapter nfcAdapter;
    String idTag;
    Button button3;
    Button zmienHaslo;
    Button przypiszLegitymace;
    private Typeface font;

    private final String[][] techList = new String[][]{
            new String[]{
                    NfcA.class.getName(),
                    IsoDep.class.getName(),
                    //MifareClassic.class.getName(),
                    Ndef.class.getName()
            }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        button3 = findViewById(R.id.button3);
        przypiszLegitymace = findViewById(R.id.button2);
        zmienHaslo = findViewById(R.id.button7);

        font = Typeface.createFromAsset( this.getAssets(), "fontawesome-webfont.ttf");

        button3.setTypeface(font);
        przypiszLegitymace.setTypeface(font);
        zmienHaslo.setTypeface(font);

        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
//        if (nfcAdapter == null) {
//            Toast.makeText(this,
//                    "NFC NOT supported on this devices!",
//                    Toast.LENGTH_LONG).show();
//            finish();
//        } else if (!nfcAdapter.isEnabled()) {
//            Toast.makeText(this,
//                    "NFC NOT Enabled!",
//                    Toast.LENGTH_LONG).show();
//            finish();
//        }
       // asd = new ZalogowanyStudent(new Studenci());


        //@SuppressLint("WrongViewCast")
       // TextView zalogowany =findViewById(R.id.tvZalogowanyJako);
        //zalogowany.setText(ZalogowanyStudent.getInstance().getZalogowanyUzytkownik().getaImie());
    }

    public void przejdzdoWyswietlZajecia(View view){
        startActivity(new Intent(this,WyswietlZajeciaActivity.class));
    }
    public void przejdzDoHasla(View view){
        startActivity(new Intent(this,ZmienSwojeHasloActivity.class));

    }
    public void przypiszELS(View view){
        receiveButton = true;
        Toast.makeText(this,"Przyłóź Legitymację",Toast.LENGTH_LONG).show();

    }


    @Override
    protected void onResume() {
        super.onResume();

        if(receiveButton) {
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
            // creating intent receiver for NFC events:
            IntentFilter filter = new IntentFilter();
            filter.addAction(NfcAdapter.ACTION_TAG_DISCOVERED);
            filter.addAction(NfcAdapter.ACTION_NDEF_DISCOVERED);
            filter.addAction(NfcAdapter.ACTION_TECH_DISCOVERED);
            // enabling foreground dispatch for getting intent from NFC event:
            NfcAdapter nfcAdapter = NfcAdapter.getDefaultAdapter(this);
            nfcAdapter.enableForegroundDispatch(this, pendingIntent, new IntentFilter[]{filter}, this.techList);
        }

        //textViewInfo.setText(tagInfo);

    }
//    @Override
//    protected void onPause() {
//        super.onPause();
//        // disabling foreground dispatch:
//        NfcAdapter nfcAdapter = NfcAdapter.getDefaultAdapter(this);
//        nfcAdapter.disableForegroundDispatch(this);
//    }
    @Override
    protected void onNewIntent(Intent intent) {
        if(receiveButton) {
            if (intent.getAction().equals(NfcAdapter.ACTION_TAG_DISCOVERED)) {
                NfcModule nfcModule = new NfcModule();
                idTag = nfcModule.ByteArrayToHexString(intent.getByteArrayExtra(NfcAdapter.EXTRA_ID));
                //textViewInfo.setText("NFC Tag\n" + ByteArrayToHexString(intent.getByteArrayExtra(NfcAdapter.EXTRA_ID)));
                //tag = intent.getParcelableExtra(NfcAdapter.EXTRA_DATA);
                // tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
                //dodajStudenta(intent);
                //textinfo.setText("dsads");
                sprawdzStudenta(intent);
                //Log.i("TagExtra", " taggeg" + tag.toString());
//            addPresenceNFC = new AddPresenceNFC();
//            addPresenceNFC.execute(ByteArrayToHexString(intent.getByteArrayExtra(NfcAdapter.EXTRA_ID)));

            }
        }

    }
    public void sprawdzStudenta(Intent intent){
       // final String studentTag = ByteArrayToHexString(intent.getByteArrayExtra(NfcAdapter.EXTRA_ID));
        final String data= getIntent().getStringExtra("ZajeciaId");
        final MobileServiceTable<Studenci> mStudentTable =  ServiceClient.getmInstance().getClient().getTable(Studenci.class);
        final String idStudent = ZalogowanyStudent.getInstance().getUserId();
        final Studenci student;
        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>(){

            @Override
            protected Void doInBackground(Void... params) {
                try {
                    final List<Studenci> results = mStudentTable.where().field("id").eq().val(idStudent).execute().get();
                    for (Studenci s :results) {
                        if(s.getstudentTag() != null){
                            Toast.makeText(StudentActivity.class.newInstance(),"Inny uzytkownik posiada juz tag",Toast.LENGTH_LONG).show();
                            return  null;
                        }
                    }
                   
                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {


                            try {
                                results.get(0).setId(idStudent);
                                results.get(0).setstudentTag(idTag);
                                Log.i("masd","asdsa" + results.get(0));
                                mStudentTable.update(results.get(0)).get();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            } catch (ExecutionException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                } catch (Exception exception) {
                    Log.i("tag","error:" + exception);
                }


                return null;
            }

        };
        task.execute();
        Toast.makeText(this,"Przypisano Kartę " ,Toast.LENGTH_SHORT).show();

    }

}
