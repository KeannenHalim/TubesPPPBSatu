package com.example.tubes1;

import java.time.LocalDateTime;

public class Pertemuan {
    private String namaDokter;
    private String namaPasien;
    private String keluhan;
    private String tanggal;
    private String waktu;
    private int id;

    public Pertemuan(String namaDokter, String namaPasien, String keluhan, String tanggal, String waktu, int id){
        this.namaDokter = namaDokter;
        this.namaPasien = namaPasien;
        this.keluhan = keluhan;
        this.tanggal = tanggal;
        this.waktu = waktu;
        this.id = id;
    }
}
