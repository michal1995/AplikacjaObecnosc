package com.example.aplikacjaObecnosc.Admin;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aplikacjaObecnosc.R;
import com.example.aplikacjaObecnosc.ServiceClient;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


public class ZmienHasloAdapter extends RecyclerView.Adapter<ZmienHasloAdapter.ViewHolder>
{
private List<Studenci> lista;
   // źródło danych
private Context context;
   // obiekt listy artykułów
   private RecyclerView mRecyclerView;

   // źródło danych

   // obiekt listy artykułów
   private Studenci studenci ;
   public Button bzmienhaslo;
   private List<Studenci> listaStudentoww = new ArrayList<>();
   private CompletableFuture progressDialog;

   private MobileServiceTable<Studenci> mStudenci = ServiceClient.getmInstance().getClient().getTable(Studenci.class);
   ZmienHasloActivity activity;

   // implementacja wzorca ViewHolder
   // każdy obiekt tej klasy przechowuje odniesienie do layoutu elementu listy
   // dzięki temu wywołujemy findViewById() tylko raz dla każdego elementu
   public class ViewHolder extends RecyclerView.ViewHolder {
      public TextView mImie;
      public TextView mNazwisko;
      public EditText mHaslo;

      public ViewHolder(View pItem) {
         super(pItem);
         mImie = (TextView) pItem.findViewById(R.id.tvimie);
         mNazwisko = (TextView) pItem.findViewById(R.id.tvnazwisko);
         mHaslo = (EditText) pItem.findViewById(R.id.etSwojeHaslo);
         bzmienhaslo = (Button) pItem.findViewById(R.id.bZmienHaslo);
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
   public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
      final Studenci article = lista.get(position);
      holder.mImie.setText(article.getaImie());
      holder.mNazwisko.setText(article.getaNazwisko());
      holder.mHaslo.setText(article.getaHaslo());
      final String nrIndeksu = article.getAnrIndeksu();

      bzmienhaslo.setOnClickListener(new View.OnClickListener() {
         @SuppressLint("StaticFieldLeak")
         @Override
         public void onClick(View v) {
            // odnajdujemy indeks klikniętego elementu
            //int positionToDelete = mRecyclerView.getChildAdapterPosition(v);
            //Toast.makeText(v.getContext(),"dsa",Toast.LENGTH_LONG);
            // Toast.makeText(v.getContext(),"jiji",Toast.LENGTH_LONG).show();

            new AsyncTask<String, String, String>() {


               @SuppressLint("WrongThread")
               @Override
               protected String doInBackground(String... params) {
                  try {
                     listaStudentoww = mStudenci.where().field("nrIndeksu").eq().val(nrIndeksu).execute().get();
                     studenci = listaStudentoww.get(0);
                     studenci.setaHaslo(String.valueOf(holder.mHaslo.getText()));
                     mStudenci.update(studenci).get();
                     Toast.makeText(context,"Zmieniono Haslo",Toast.LENGTH_LONG).show();

                  } catch (InterruptedException e) {
                     e.printStackTrace();
                  } catch (ExecutionException e) {
                     e.printStackTrace();
                  }
                  //studenci = listaStudentoww.get(0);
                  //  tmp.setaHaslo("hihi");
                  // mStudenci.update(tmp).get();
                  //listaStudentoww=zwrocStudentow();
                  //listaStudentoww.add(position,article);


                  //  mStudenci.update(studenci).get();


                  return null;
               }
            }.execute();

            // usuwamy element ze źródła danych
            //lista.remove(positionToDelete);
            // poniższa metoda w animowany sposób usunie element z listy
            //notifyItemRemoved(positionToDelete);
         }
      });

   }



   @Override
   public int getItemCount() {
      return lista.size();
   }


}