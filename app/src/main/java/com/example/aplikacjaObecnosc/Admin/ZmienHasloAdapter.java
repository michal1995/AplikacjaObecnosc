package com.example.aplikacjaObecnosc.Admin;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.aplikacjaObecnosc.R;

import java.util.ArrayList;
import java.util.List;


public class ZmienHasloAdapter extends RecyclerView.Adapter<ZmienHasloAdapter.ViewHolder>
{
private List<Studenci> lista;
   // źródło danych
private Context context;
   // obiekt listy artykułów
   private RecyclerView mRecyclerView;

   // implementacja wzorca ViewHolder
   // każdy obiekt tej klasy przechowuje odniesienie do layoutu elementu listy
   // dzięki temu wywołujemy findViewById() tylko raz dla każdego elementu
   public class ViewHolder extends RecyclerView.ViewHolder {
      public TextView mImie;
      public TextView mNazwisko;

      public ViewHolder(View pItem) {
         super(pItem);
         mImie = (TextView) pItem.findViewById(R.id.tvimie);
         mNazwisko = (TextView) pItem.findViewById(R.id.tvnazwisko);
      }
   }



   // konstruktor adaptera
   public ZmienHasloAdapter(Context contekstAplikacja, List<Studenci> liste){
      this.context = contekstAplikacja;
      this.lista = liste;
   }

   @Override
   public ViewHolder onCreateViewHolder(ViewGroup viewGroup, final int i) {
      // tworzymy layout artykułu oraz obiekt ViewHoldera
      View view = LayoutInflater.from(viewGroup.getContext())
              .inflate(R.layout.wiersz_haslo, viewGroup, false);

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
      return new ViewHolder(view);
   }

   @Override
   public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
      Studenci article = lista.get(position);
      holder.mImie.setText(article.getaImie());
      holder.mNazwisko.setText(article.getaNazwisko());
   }



   @Override
   public int getItemCount() {
      return lista.size();
   }


}