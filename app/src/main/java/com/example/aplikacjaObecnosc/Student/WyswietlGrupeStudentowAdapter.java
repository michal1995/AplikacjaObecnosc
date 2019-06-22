package com.example.aplikacjaObecnosc.Student;

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

import com.example.aplikacjaObecnosc.Admin.Studenci;
import com.example.aplikacjaObecnosc.R;
import com.example.aplikacjaObecnosc.ServiceClient;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;

public class WyswietlGrupeStudentowAdapter extends ArrayAdapter<Studenci> {
    private final Context mContext;
    private final int mLayoutResourceId;

    private MobileServiceTable<Studenci> mStudenci = ServiceClient.getmInstance().getClient().getTable(Studenci.class);


    public WyswietlGrupeStudentowAdapter(Context context,int layoutResourceId) {
        super(context, layoutResourceId);
        mContext = context;
        mLayoutResourceId = layoutResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable final View convertView, @NonNull ViewGroup parent) {

        View row = convertView;
        final Studenci currentItem = getItem(position);
        if (row == null) {
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            row = inflater.inflate(mLayoutResourceId, parent, false); }

        row.setTag(currentItem);


        final TextView item_imie= row.findViewById(R.id.tvGrupaImie);
        final TextView item_nazwisko = row.findViewById(R.id.tvGrupaNazwisko);
        final  TextView item_Pid = row.findViewById(R.id.tvGrupaKolejnosc);
        final TextView item_indeks = row.findViewById(R.id.tvGrupaIndeks);


        item_imie.setText(currentItem.getaImie());
        item_nazwisko.setText(currentItem.getaNazwisko());
        position += 1;
        item_Pid.setText(String.valueOf(position));
        item_indeks.setText(currentItem.getAnrIndeksu());
        final String nr_id = currentItem.getId();

        final View finalRow = row;
        /*.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                //  WyswietlStudentowActivity activity = (WyswietlStudentowActivity) mContext;
                // activity.usunDoListyWybranych(currentItem);
                // mMobileSeriveStable.delete(currentItem);
                // Toast.makeText(arg0.getContext(),"jiji",Toast.LENGTH_LONG).show();
                mStudenci.delete(nr_id);
                finalRow.setVisibility(View.INVISIBLE);

            }
        });*/

        return row;
    }


    @Override
    public boolean isEnabled(int position) {
        return super.isEnabled(position);
    }
}
