package com.example.aplikacjaObecnosc.Admin;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.content.Context;
import android.widget.EditText;
import android.widget.TextView;

import com.example.aplikacjaObecnosc.R;

public class WyswietlStudentowAdapter extends ArrayAdapter<Studenci> {

    private final Context mContext;
    private final int mLayoutResourceId;

    public WyswietlStudentowAdapter(Context context,int layoutResourceId) {
        super(context, layoutResourceId);
        mContext = context;
        mLayoutResourceId = layoutResourceId;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View row = convertView;
        final Studenci currentItem = getItem(position);
        if (row == null) {
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            row = inflater.inflate(mLayoutResourceId, parent, false);
        }

        row.setTag(currentItem);

        TextView list_item= row.findViewById(R.id.list_item);
        EditText etImie= row.findViewById(R.id.etImie);
list_item.setText(currentItem.getaEmail());

        return row;
    }
    @Override
    public boolean isEnabled(int position) {
        return super.isEnabled(position);
    }
}
