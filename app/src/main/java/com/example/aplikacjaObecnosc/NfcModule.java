package com.example.aplikacjaObecnosc;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.IsoDep;
import android.nfc.tech.Ndef;
import android.nfc.tech.NfcA;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class NfcModule extends AppCompatActivity {
    private NfcAdapter nfcAdapter;
    TextView textViewInfo;
    TextView textinfo;
    Button circleButon;
    Tag tag;
    Boolean startListen= false;
    Integer click =0;
    AddPresenceNFC addPresenceNFC;
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
        setContentView(R.layout.activity_nfc_module);

        textViewInfo = findViewById(R.id.textView3);
        textinfo = findViewById(R.id.textView18);
        circleButon = findViewById(R.id.circle_button);
        nfcAdapter = NfcAdapter.getDefaultAdapter(this);

        if (nfcAdapter == null) {
            Toast.makeText(this,
                    "NFC NOT supported on this devices!",
                    Toast.LENGTH_LONG).show();
            finish();
        } else if (!nfcAdapter.isEnabled()) {
            Toast.makeText(this,
                    "NFC NOT Enabled!",
                    Toast.LENGTH_LONG).show();
            finish();
        }
    }

    public void onClickButtonStartListenNfc(View view){

        click = click + 1;

        if(click%2 == 0) {
            startListen = false;
            Toast.makeText(this, "Nasłuchiwanie przerwano", Toast.LENGTH_LONG).show();
        }else{
            startListen = true;
            Toast.makeText(this, "Rozpoczeto przerwano", Toast.LENGTH_LONG).show();
        }


    }

    @Override
    protected void onResume() {
        super.onResume();

        if(startListen == true) {
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



    @Override
    protected void onPause() {
        super.onPause();
        // disabling foreground dispatch:
        NfcAdapter nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        nfcAdapter.disableForegroundDispatch(this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        if (intent.getAction().equals(NfcAdapter.ACTION_TAG_DISCOVERED)) {
            textViewInfo.setText("NFC Tag\n" + ByteArrayToHexString(intent.getByteArrayExtra(NfcAdapter.EXTRA_ID)));
            //tag = intent.getParcelableExtra(NfcAdapter.EXTRA_DATA);
            tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);

            textinfo.setText("dsads");

            Log.i("TagExtra", " taggeg" + tag.toString());
            addPresenceNFC = new AddPresenceNFC(this,this);
            addPresenceNFC.execute(ByteArrayToHexString(intent.getByteArrayExtra(NfcAdapter.EXTRA_ID)));

        }


    }

    private String ByteArrayToHexString(byte[] inarray) {
        int i, j, in;
        String[] hex = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F"};
        String out = "";

        for (j = 0; j < inarray.length; ++j) {
            in = (int) inarray[j] & 0xff;
            i = (in >> 4) & 0x0f;
            out += hex[i];
            i = in & 0x0f;
            out += hex[i];
        }
        return out;
    }

}



