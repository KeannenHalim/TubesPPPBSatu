package com.example.tubes1;

public class Dokter {
    private String nama;
    private String specialist;

    public Dokter(String nama, String specialist){
        this.nama = nama;
        this.specialist = specialist;
    }

    public String getNama(){
        return this.nama;
    }

    public String getSpecialist(){
        return this.specialist;
    }
}
