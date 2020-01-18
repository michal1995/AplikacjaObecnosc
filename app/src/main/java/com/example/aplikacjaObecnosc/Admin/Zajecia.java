package com.example.aplikacjaObecnosc.Admin;

//import java.sql.Date;

public class Zajecia {


@com.google.gson.annotations.SerializedName("id")
private String aId;
    public String getaId() {return aId;}
    public  void  setaId(String aId){this.aId=aId;  }

    @com.google.gson.annotations.SerializedName("TematZajec")
   private String TematZajec;
    public String getTematZajec() {
        return TematZajec;
    }
    public void setTematZajec(String tematZajec) {
        TematZajec = tematZajec;
    }



    @com.google.gson.annotations.SerializedName("Lokalizacja")
    private String Lokalizacja;
    public String getLokalizacja() {
        return Lokalizacja;
    }

    public void setLokalizacja(String lokalizacja) {
        Lokalizacja = lokalizacja;
    }



    @com.google.gson.annotations.SerializedName("Czas")
    private String Czas;
    public String getData() {
        return Czas;
    }
    public void setData(String Czas) {
        this.Czas = Czas;
    }

    @com.google.gson.annotations.SerializedName("idTeacher")
    private String idTeacher;
    public String getidTeacher() {
        return idTeacher;
    }
    public void setidTeacher(String idTeacher) {
        this.idTeacher = idTeacher;
    }

    @com.google.gson.annotations.SerializedName("idGrupa")
    private String idGrupa;
    public String getidGrupa() {
        return idGrupa;
    }
    public void setidGrupa(String idGrupa) {
        this.idGrupa = idGrupa;
    }

    @com.google.gson.annotations.SerializedName("Obecnosc")
    private Boolean Obecnosc;
    public Boolean getObecnosc() {
        return Obecnosc;
    }
    public void setObecnosc(Boolean Obecnosc) {
        this.Obecnosc = Obecnosc;
    }




}
