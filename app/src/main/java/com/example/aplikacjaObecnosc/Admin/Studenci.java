package com.example.aplikacjaObecnosc.Admin;

public class Studenci {
    @com.google.gson.annotations.SerializedName("id")
    private String aId;
         public String getaId() {return aId;}
            public  void  setaId(String aId){this.aId=aId;  }
    @com.google.gson.annotations.SerializedName("Imie")
    private String aImie;
         public String getaImie(){return aImie;}
            public void setaImie(String aImie){this.aImie = aImie;}


    @com.google.gson.annotations.SerializedName("Nazwisko")
    private String aNazwisko;
    public String getaNazwisko() {
        return aNazwisko;
    }

    public void setaNazwisko(String aNazwisko) {
        this.aNazwisko = aNazwisko;
    }



    @com.google.gson.annotations.SerializedName("email")
    private  String aEmail;
    public String getaEmail() {
        return aEmail;
    }

    public void setaEmail(String aEmail) {
        this.aEmail = aEmail;
    }
    @com.google.gson.annotations.SerializedName("Haslo")
    private String aHaslo;

    public String getaHaslo() {
        return aHaslo;
    }

    public void setaHaslo(String aHaslo) {
        this.aHaslo = aHaslo;
    }
    @com.google.gson.annotations.SerializedName("nrIndeksu")
    private String anrIndeksu;

    public String getAnrIndeksu() {
        return anrIndeksu;
    }

    public void setAnrIndeksu(String anrIndeksu) {
        this.anrIndeksu = anrIndeksu;
    }
   /* @com.google.gson.annotations.SerializedName("PId")

    private Integer PId;


    public Integer getPId() {
        return PId;
    }

    public void setPid(Integer PId) {
        this.PId = PId;
    }*/
}
