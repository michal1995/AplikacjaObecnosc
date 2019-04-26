package com.example.aplikacjaObecnosc;


import android.content.Context;
import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;
import java.net.MalformedURLException;

public class ServiceClient {
    /**
     * Client połączenia z serwerem
     */
    private MobileServiceClient mClient;
    private Context mContext;
    private String mMobileBackendUrl = "https://aplikacjazarzadzaniadlugiem.azurewebsites.net";

    public MobileServiceTable<Uzytkownicy> getmUzytkownikTable() {
        return mUzytkownikTable;
    }

    private MobileServiceTable<Uzytkownicy> mUzytkownikTable;





    private static ServiceClient mInstance;

    /**
     * Prywatny konstruktor tworzy instancje MobileServiceClient
     *
     * @param context
     * @throws MalformedURLException
     */
    private ServiceClient(Context context) throws MalformedURLException {
        mContext = context;
        mClient = new MobileServiceClient(mMobileBackendUrl, mContext);
        mUzytkownikTable = mClient.getTable(Uzytkownicy.class);

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