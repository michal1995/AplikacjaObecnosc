package com.example.aplikacjaObecnosc;

import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.IsoDep;
import android.nfc.tech.MifareClassic;
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
import com.example.aplikacjaObecnosc.Admin.Zajecia;
import com.example.aplikacjaObecnosc.Student.Grupa;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class NfcModule extends AppCompatActivity {
    private NfcAdapter nfcAdapter;
    TextView textViewInfo;
    TextView textinfo;
    Button circleButon;
    Tag tag;
    Boolean startListen= false;
    Integer click =0;
    AddPresenceNFC addPresenceNFC;
    Zajecia mZajecia;
    Studenci mStudenci;
    List<Studenci> listaStudenci;
    private MobileServiceTable<Studenci> mStudentTable =  ServiceClient.getmInstance().getClient().getTable(Studenci.class);
    private MobileServiceTable<Grupa> mGrupaTable = ServiceClient.getmInstance().getClient().getTable(Grupa.class);
    Grupa mGrupa;
    ProgressDialog progressDialog;
    MifareClassic mfc;
    private final String[][] techList = new String[][]{
            new String[]{
                    NfcA.class.getName(),
                    IsoDep.class.getName(),
                    MifareClassic.class.getName(),
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
            Toast.makeText(this, "Nasłuchiwanie Rozpoczeto", Toast.LENGTH_LONG).show();
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
            //textViewInfo.setText("NFC Tag\n" + ByteArrayToHexString(intent.getByteArrayExtra(NfcAdapter.EXTRA_ID)));
            //tag = intent.getParcelableExtra(NfcAdapter.EXTRA_DATA);
            tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
               // dodajStudenta(intent);

            //textinfo.setText(NfcAdapter.ACTION_TAG_DISCOVERED);
            textViewInfo.setText(readTagClassic(tag));
            //NfcA.get(tag);
            //Log.i("TagExtra", " taggeg" + tag.toString());
//            addPresenceNFC = new AddPresenceNFC();
//            addPresenceNFC.execute(ByteArrayToHexString(intent.getByteArrayExtra(NfcAdapter.EXTRA_ID)));

        }


    }

    public void dodajStudenta(Intent intent){
      final String studentTag = ByteArrayToHexString(intent.getByteArrayExtra(NfcAdapter.EXTRA_ID));
      final String data= getIntent().getStringExtra("ZajeciaId");
        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>(){

            @Override
            protected Void doInBackground(Void... params) {
                try {
                    final List<Studenci> results = mStudentTable.where().field("studentTag").eq().val(studentTag).execute().get();
                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            mGrupa = new Grupa();
                            mGrupa.setStudentId(results.get(0).getId());
                            mGrupa.setZajeciaId(data);
                            mGrupa.setObecnosc(true);

                            try {
                                mGrupaTable.insert(mGrupa).get();
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
        Toast.makeText(this,"Dodano studenta " ,Toast.LENGTH_SHORT).show();

    }

    public String ByteArrayToHexString(byte[] inarray) {
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

    public String readTagClassic(Tag tag) {
        boolean auth = false;
        mfc = MifareClassic.get(tag);
        // 读取TAG
        NfcA asd = NfcA.get(tag);


        Log.i("tagNfca",": " + asd.getAtqa());
        Log.i("tagNfcaa",": " + mfc.getBlockCount());

        try {
            String metaInfo = "";
            int type = mfc.getType();// 获取TAG的类型
            int sectorCount = mfc.getSectorCount();// 获取TAG中包含的扇区数
            String typeS = "";
            switch (type) {
                case MifareClassic.TYPE_CLASSIC:
                    typeS = "TYPE_CLASSIC";
                    break;
                case MifareClassic.TYPE_PLUS:
                    typeS = "TYPE_PLUS";
                    break;
                case MifareClassic.TYPE_PRO:
                    typeS = "TYPE_PRO";
                    break;
                case MifareClassic.TYPE_UNKNOWN:
                    typeS = "TYPE_UNKNOWN";
                    break;
            }
            metaInfo += "卡片类型：" + typeS + "\n共" + sectorCount + "个扇区\n共"
                    + mfc.getBlockCount() + "个块\n存储空间: " + mfc.getSize()
                    + "B\n";
            for (int j = 0; j < sectorCount; j++) {
                // Authenticate a sector with key A.

                auth = mfc.authenticateSectorWithKeyA(j,
                        MifareClassic.KEY_DEFAULT);
                int bCount;
                int bIndex;
                if (auth) {
                    metaInfo += "Sector " + j + ":验证成功\n";
                    // 读取扇区中的块
                    bCount = mfc.getBlockCountInSector(j);
                    bIndex = mfc.sectorToBlock(j);
                    for (int i = 0; i < bCount; i++) {
                        byte[] data = mfc.readBlock(bIndex);
                        metaInfo += "Block " + bIndex + " : "
                                + ByteArrayToHexString(data) + "\n";
                        bIndex++;
                    }
                } else {
                    metaInfo += "Sector " + j + ":验证失败\n";
                }
            }
            return metaInfo;
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        } finally {
            if (mfc != null) {
                try {
                    mfc.close();
                } catch (IOException e) {
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG)
                            .show();
                }
            }
        }
        return null;

    }

}



