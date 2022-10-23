package com.example.tubes1;

import java.time.LocalDateTime;

public class Pertemuan {
    private String namaDokter;
    private String namaPasien;
    private String keluhan;
    private String tanggal;
    private String waktu;
    private Boolean status;
    private int id;

    public String getNamaDokter() {
        return namaDokter;
    }

    public void setNamaDokter(String namaDokter) {
        this.namaDokter = namaDokter;
    }



    public String getNamaPasien() {
        return namaPasien;
    }

    public void setNamaPasien(String namaPasien) {
        this.namaPasien = namaPasien;
    }

    public String getKeluhan() {
        return keluhan;
    }

    public void setKeluhan(String keluhan) {
        this.keluhan = keluhan;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public Pertemuan(String namaDokter, String namaPasien, String keluhan, String tanggal, String waktu, int id, boolean status){
        this.namaDokter = namaDokter;
        this.namaPasien = namaPasien;
        this.keluhan = keluhan;
        this.tanggal = tanggal;
        this.waktu = waktu;
        this.id = id;
        this.status = status;
    }


}
