package com.example.aplikacjaObecnosc.Student;

public class Grupa {




    @com.google.gson.annotations.SerializedName("id")
    private String id;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @com.google.gson.annotations.SerializedName("StudentId")
    private String StudentId;


    public String getStudentId() {
        return StudentId;
    }

    public void setStudentId(String studentId) {
        StudentId = studentId;
    }



    @com.google.gson.annotations.SerializedName("ZajeciaId")
    
    private String ZajeciaId;
    public String getZajeciaId() {
        return ZajeciaId;
    }

    public void setZajeciaId(String zajeciaId) {
        ZajeciaId = zajeciaId;
    }

    @com.google.gson.annotations.SerializedName("Obecnosc")

    private boolean Obecnosc;

    public boolean getObecnosc() {
        return Obecnosc;
    }

    public void setObecnosc(boolean obecnosc) { Obecnosc = obecnosc; }



}
