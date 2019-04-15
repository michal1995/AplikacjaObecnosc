package com.example.aplikacjaObecnosc;


import android.content.Context;
import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;
import java.net.MalformedURLException;

public class ServiceClient {
    private static ServiceClient mInstance;
    private MobileServiceClient mClient;
    private Context mContext;
    private String mMobileBackendUrl = "https://aplikacjazarzadzaniadlugiem.azurewebsites.net";
    private MobileServiceTable<Uzytkownicy> mUzytkownikTable;

    private ServiceClient(Context paramContext)
            throws MalformedURLException {
        this.mContext = paramContext;
        this.mClient = new MobileServiceClient(this.mMobileBackendUrl, this.mContext);
        this.mUzytkownikTable = this.mClient.getTable(Uzytkownicy.class);
    }

    public static void Initialize(Context paramContext)
            throws MalformedURLException {
        if (mInstance == null) {
            mInstance = new ServiceClient(paramContext);
        }
    }

    public static ServiceClient getmInstance() {
        return mInstance;
    }

    public MobileServiceClient getClient() {
        return this.mClient;
    }

    public MobileServiceTable<Uzytkownicy> getmUzytkownikTable() {
        return this.mUzytkownikTable;
    }
}