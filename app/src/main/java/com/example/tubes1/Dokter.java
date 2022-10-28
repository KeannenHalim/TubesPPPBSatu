package com.example.tubes1;

import java.io.Serializable;

public class Dokter{
    private String nama;
    private String specialist;
    private String noTelepon;
    private int id;
    public Dokter(String nama, String specialist, String noTelepon, int id){
        this.nama = nama;
        this.specialist = specialist;
        this.noTelepon = noTelepon;
        this.id = id;
    }
    public void setNama(String nama){
        this.nama = nama;
    }

    public void setSpecialist(String specialist){
        this.specialist = specialist;
    }

    public void setNoTelepon(String telepon){
        this.noTelepon = telepon;
    }
    public String getNama(){
        return this.nama;
    }

    public String getSpecialist(){
        return this.specialist;
    }

    public String getNoTelepon(){return this.noTelepon;}

    public int getId(){
        return this.id;
    }

    public void setId(int id){
        this.id = id;
    }
}
