package com.example.aplikacjaObecnosc.Student;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.aplikacjaObecnosc.Admin.Studenci;
import com.example.aplikacjaObecnosc.Admin.WyswietlStudentowActivity;
import com.example.aplikacjaObecnosc.Admin.Zajecia;
import com.example.aplikacjaObecnosc.Admin.ZmienHasloActivity;
import com.example.aplikacjaObecnosc.Admin.ZmienHasloAdapter;
import com.example.aplikacjaObecnosc.R;
import com.example.aplikacjaObecnosc.ServiceClient;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;

import java.util.List;
import java.util.concurrent.ExecutionException;

import static android.support.v4.content.ContextCompat.startActivity;

public class WyswietlZajeciaAdapter extends RecyclerView.Adapter<WyswietlZajeciaAdapter.ViewHolder>{
        private MobileServiceTable<Zajecia> mStudenci = ServiceClient.getmInstance().getClient().getTable(Zajecia.class);
        WyswietlZajeciaActivity activity;
        List<Zajecia> lista;
        Context context;
        public Button listaObecnosci;
// implementacja wzorca ViewHolder
// każdy obiekt tej klasy przechowuje odniesienie do layoutu elementu listy
// dzięki temu wywołujemy findViewById() tylko raz dla każdego elementu
public class ViewHolder extends RecyclerView.ViewHolder {

    public TextView tematt;
    public TextView lokalizacjaa;
    public TextView czass;


    public ViewHolder(View pItem) {
        super(pItem);
        tematt = (TextView) pItem.findViewById(R.id.tvTematt);
        lokalizacjaa = (TextView) pItem.findViewById(R.id.tvLokalizacjaa);
        czass = (TextView) pItem.findViewById(R.id.tvCzass);
        listaObecnosci = (Button) pItem.findViewById(R.id.bListaObecnosci);
    }
}



    // konstruktor adaptera
    public WyswietlZajeciaAdapter(Context contekstAplikacja, List<Zajecia> liste){
        this.context = contekstAplikacja;
        this.lista = liste;
    }

    @Override
    public WyswietlZajeciaAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, final int i) {
        // tworzymy layout artykułu oraz obiekt ViewHoldera
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.wiersz_zajecia_student, viewGroup, false);

        // dla elementu listy ustawiamy obiekt OnClickListener,
        // który usunie element z listy po kliknięciu na niego
     /* view.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            // odnajdujemy indeks klikniętego elementu
            int positionToDelete = mRecyclerView.getChildAdapterPosition(v);
            // usuwamy element ze źródła danych
            lista.remove(positionToDelete);
            // poniższa metoda w animowany sposób usunie element z listy
            notifyItemRemoved(positionToDelete);
         }
      });*/

        // tworzymy i zwracamy obiekt ViewHolder
        return new WyswietlZajeciaAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    final Zajecia zajecia = lista.get(position);
        holder.tematt.setText(zajecia.getTematZajec());
        holder.lokalizacjaa.setText(zajecia.getLokalizacja());
        holder.czass.setText(zajecia.getData());
       listaObecnosci.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent i = new Intent(v.getContext(),WyswietlGrupeStudentowActivity.class);
               v.getContext().startActivity(i);
           }
       });



    }


    @Override
    public int getItemCount() {
        return lista.size();
    }


}