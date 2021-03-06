package com.example.aplikacjaObecnosc.Admin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.aplikacjaObecnosc.NfcModule;
import com.example.aplikacjaObecnosc.R;
import com.example.aplikacjaObecnosc.ServiceClient;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;

import java.util.ArrayList;
import java.util.List;

import static android.support.v4.content.ContextCompat.startActivity;


public class DodajZajeciaAdapter extends ArrayAdapter<Zajecia> {

    private TextView item_temat;
    private TextView item_lokalizacja;
    private TextView item_czas;
    private Button item_usun;
    private MobileServiceTable<Zajecia> mZajecia = ServiceClient.getmInstance().getmZajeciaTable();
    private Typeface font;
    private Boolean misNFC ;


    private final Context mContext;
    private final int ResourceId;
    public DodajZajeciaAdapter(@NonNull Context context, int resource, boolean isNFC) {
        super(context, resource);
        mContext = context;
        ResourceId = resource;
        misNFC = isNFC;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable final View convertView, @NonNull ViewGroup parent) {
       // return super.getView(position, convertView, parent);
        View row = convertView;
        final Zajecia currentItem = getItem(position);
        if (row == null) {
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            row = inflater.inflate(ResourceId, parent, false); }

        row.setTag(currentItem);


        item_temat = row.findViewById(R.id.tvTemat);
        item_temat.setText(currentItem.getTematZajec());

        item_lokalizacja = row.findViewById(R.id.tvLokalizacja);
        item_lokalizacja.setText(currentItem.getLokalizacja());
        item_czas = row.findViewById(R.id.tvCzas);
        item_czas.setText(currentItem.getData());
       item_usun = row.findViewById(R.id.bUsunZajecia);

        if(!misNFC) {
            item_usun.setText(mContext.getResources().getString(R.string.fa_trash));
            font = Typeface.createFromAsset(getContext().getAssets(), "fontawesome-webfont.ttf");
            item_usun.setTypeface(font);
        }else{
            item_usun.setText(mContext.getResources().getString(R.string.fa_id_card_alt));
            font = Typeface.createFromAsset(getContext().getAssets(), "fontawesome-webfont.ttf");
            item_usun.setTypeface(font);
        }
        final String nr_id = currentItem.getaId();

        final View finalRow = row;
        item_usun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(misNFC) {
                    Intent intent = new Intent(v.getContext(), NfcModule.class);
                    intent.putExtra("ZajeciaId", currentItem.getaId());
                    startActivity(getContext(), intent, null);
                }else{

                    mZajecia.delete(nr_id);
                    finalRow.setVisibility(View.INVISIBLE);

                }
            }
        });

        return row;
    }

    @Override
    public boolean isEnabled(int position) {
        return super.isEnabled(position);
    }
}
