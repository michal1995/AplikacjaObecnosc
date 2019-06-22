package com.example.aplikacjaObecnosc;
import android.content.Context;

import com.example.aplikacjaObecnosc.Admin.Studenci;
import com.example.aplikacjaObecnosc.Admin.Zajecia;
import com.example.aplikacjaObecnosc.Student.Grupa;
import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;
import java.net.MalformedURLException;
import java.util.List;

public class ServiceClient {
    /**
     * Client połączenia z serwerem
     */
    private MobileServiceClient mClient;
    private Context mContext;
    private String mMobileBackendUrl = "https://aplikacjazarzadzaniadlugiem.azurewebsites.net";
    private MobileServiceTable<Studenci> mStudentTable;
    public MobileServiceTable<Studenci> getmStudentTable() { return mStudentTable; }
    private MobileServiceTable<Uzytkownicy> mUzytkownikTable;
    public MobileServiceTable<Uzytkownicy> getmUzytkownikTable(){return mUzytkownikTable;}
    private MobileServiceTable<Zajecia> mZajeciaTable;
    public MobileServiceTable<Zajecia> getmZajeciaTable() { return mZajeciaTable; }
    private MobileServiceTable<Grupa> mGrupa;
    public MobileServiceTable<Grupa> getmGrupa(){return mGrupa;}
    private static ServiceClient mInstance =null;

    /**
     * Prywatny konstruktor tworzy instancje MobileServiceClient
     *
     * @param context
     * @throws MalformedURLException
     */
    private ServiceClient(Context context) throws MalformedURLException {
        mContext = context;
        mClient = new MobileServiceClient(mMobileBackendUrl, mContext);
        this.mUzytkownikTable = mClient.getTable(Uzytkownicy.class);
        this.mStudentTable = mClient.getTable(Studenci.class);
        this.mZajeciaTable = mClient.getTable(Zajecia.class);
        this.mGrupa = mClient.getTable(Grupa.class);
    }


    /**
     * Inicjalizacja statyczna obiektu ServiceClient jeśli nie istnieje
     *
     * @param context
     * @throws MalformedURLException
     */
    public static void Initialize(Context context) throws MalformedURLException {

        if (mInstance == null) {
            mInstance = new ServiceClient(context);
        }

    }

    public MobileServiceClient getClient() {
        return mClient;
    }
    public static ServiceClient getmInstance()
    {
        return mInstance;
    }

}
