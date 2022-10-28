package com.example.tubes1;

import android.app.Activity;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Presenter{
    private IDokter uiD;
    private IPertemuan uiP;
    private IDokterDropdown uiDdown;
    private List<Dokter> list_dokter;

    private List<Pertemuan> list_pertemuan;
    private Activity activity;
    private FeedReaderDbHelper dbHelper;

    private DaftarDokter daftarDokter;
    private DaftarPertemuan daftarPertemuan;

    public Presenter(IDokter uiD, IPertemuan uiP, IDokterDropdown uiDdown, Activity activity){
        this.uiD = uiD;
        this.uiP = uiP;
        this.uiDdown = uiDdown;
        this.list_dokter = new ArrayList<>();
        this.list_pertemuan = new ArrayList<>();
        this.activity = activity;
        this.dbHelper = new FeedReaderDbHelper(this.activity);
        this.daftarDokter = new DaftarDokter(this.dbHelper);
        this.daftarPertemuan = new DaftarPertemuan(this.dbHelper);
    }

    public void addDokter(Dokter newDokter){
        this.list_dokter.add(this.daftarDokter.addToDb(newDokter));
        this.uiD.updateListDokter(this.list_dokter);
    }

    public void deleteDokter(int position){
        Dokter temp = this.list_dokter.remove(position);
        this.daftarDokter.deleteFromDb(temp.getId());
        this.uiD.updateListDokter(this.list_dokter);
    }

    public void loadDokter(){
        this.list_dokter.clear();
        this.list_dokter.addAll(this.daftarDokter.getFromDb());
        this.uiD.updateListDokter(this.list_dokter);
    }

    public void loadDokterFilter(String filter){
        this.list_dokter.clear();
        this.list_dokter.addAll(this.daftarDokter.getFromDbFilter(filter));
        this.uiD.updateListDokter(this.list_dokter);
    }

    public void loadDokterDropdown(){
        this.list_dokter.clear();
        this.list_dokter.addAll(this.daftarDokter.getFromDb());
        this.uiDdown.updateDropdown(this.list_dokter);
    }

    public void toggleStatus(int idx){
        Pertemuan temp =  this.list_pertemuan.get(idx);
        temp.toggleStatus();
        this.daftarPertemuan.updateFromDb(temp);
        this.uiP.updateListPertemuan(this.list_pertemuan);
    }

    public void addPertemuan(Pertemuan newPertemuan){
        this.list_pertemuan.add(this.daftarPertemuan.addToDb(newPertemuan));
        this.uiP.updateListPertemuan(this.list_pertemuan);
    }

    public void deletePertemuan(int position){
        Pertemuan temp = this.list_pertemuan.remove(position);
        this.daftarPertemuan.deleteFromDb(temp.getId());
        this.uiP.updateListPertemuan(this.list_pertemuan);
    }

    public void loadPertemuan(){
        this.list_pertemuan.clear();
        this.list_pertemuan.addAll(this.daftarPertemuan.getFromDb());
        this.uiP.updateListPertemuan(this.list_pertemuan);
    }

}
