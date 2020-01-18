package com.example.aplikacjaObecnosc.Admin;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.aplikacjaObecnosc.R;
import com.example.aplikacjaObecnosc.ServiceClient;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;

public class WyswietlStudentowAdapter extends ArrayAdapter<Studenci> {

    private final Context mContext;
    private final int mLayoutResourceId;
Activity activity;
    private MobileServiceTable<Studenci> mStudenci = ServiceClient.getmInstance().getClient().getTable(Studenci.class);


    public WyswietlStudentowAdapter(Context context,int layoutResourceId,Activity activity) {
        super(context, layoutResourceId);
        mContext = context;
        mLayoutResourceId = layoutResourceId;
        this.activity = activity;
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

       final TextView item_email= row.findViewById(R.id.tvGrupaEmail);
       final TextView item_imie= row.findViewById(R.id.tvImie);
       final TextView item_nazwisko = row.findViewById(R.id.tvNazwisko);
       final  TextView item_Pid = row.findViewById(R.id.tvKolejnosc);
       final Button item_usun = row.findViewById(R.id.bUsunStudent);
       final TextView item_indeks = row.findViewById(R.id.tvIndeks);
       final TextView item_Haslo = row.findViewById(R.id.tvHaslo);

            item_email.setText(currentItem.getaEmail());
            item_imie.setText(currentItem.getaImie());
            item_nazwisko.setText(currentItem.getaNazwisko());
            position += 1;
            item_Pid.setText(String.valueOf(position));
            item_indeks.setText(currentItem.getAnrIndeksu());
        item_Haslo.setText(currentItem.getaHaslo());
            final String nr_id = currentItem.getId();

        final View finalRow = row;
        item_usun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                //  WyswietlStudentowActivity activity = (WyswietlStudentowActivity) mContext;
                 // activity.usunDoListyWybranych(currentItem);
                // mMobileSeriveStable.delete(currentItem);
                // Toast.makeText(arg0.getContext(),"jiji",Toast.LENGTH_LONG).show();
                mStudenci.delete(nr_id);
                //finalRow.setVisibility(View.INVISIBLE);
                activity.recreate();


            }
            });

        return row;
    }


    @Override
    public boolean isEnabled(int position) {
        return super.isEnabled(position);
    }
}
