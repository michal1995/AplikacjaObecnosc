package com.example.aplikacjaObecnosc.Admin;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.aplikacjaObecnosc.R;
import com.example.aplikacjaObecnosc.ServiceClient;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;

import java.util.ArrayList;

public class DodajZajeciaAdapter extends ArrayAdapter<Zajecia> {

    private TextView item_temat;
    private TextView item_lokalizacja;
    private TextView item_czas;
    private ImageButton item_usun;
    private MobileServiceTable<Zajecia> mZajecia = ServiceClient.getmInstance().getClient().getTable(Zajecia.class);


    private final Context mContext;
    private final int ResourceId;
    public DodajZajeciaAdapter(@NonNull Context context, int resource) {
        super(context, resource);
        mContext = context;
        ResourceId = resource;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
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
        final String nr_id = currentItem.getaId();

        final View finalRow = row;
        item_usun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mZajecia.delete(nr_id);
                finalRow.setVisibility(View.INVISIBLE);
            }
        });

        return row;
    }

    @Override
    public boolean isEnabled(int position) {
        return super.isEnabled(position);
    }
}
