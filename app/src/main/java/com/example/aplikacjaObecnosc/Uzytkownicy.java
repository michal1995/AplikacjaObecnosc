package com.example.aplikacjaObecnosc;

public class Uzytkownicy {


    @com.google.gson.annotations.SerializedName("id")
    private String mId;
    @com.google.gson.annotations.SerializedName("idUser")
    private int idUser;
    @com.google.gson.annotations.SerializedName("login")
    private String mLogin;

    @com.google.gson.annotations.SerializedName("haslo")
    private String mHaslo;

    public String getmHaslo() {
        return mHaslo;
    }

    public int getIdUser() {
        return idUser;
    }

    public String getmId() {
        return mId;
    }

    public String getmLogin() {
        return mLogin;
    }

    public void setmLogin(String mLogin) {
        this.mLogin = mLogin;
    }

    public void setmHaslo(String mHaslo) {
        this.mHaslo = mHaslo;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }


}