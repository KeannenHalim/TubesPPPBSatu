package com.example.tubes1;

import java.util.ArrayList;
import java.util.List;

public class Presenter{
    private IDokter uiD;
    private IPertemuan uiP;
    private List<Dokter> list_dokter;
    private List<Dokter> tempNewDokter;
    private List<Integer> tempDeleteDokter;
    private List<Pertemuan> list_pertemuan;
    private List<Pertemuan> tempNewPertemuan;
    private List<Integer> tempDeletePertemuan;
    public Presenter(IDokter uiD, IPertemuan uiP){
        this.uiD = uiD;
        this.uiP = uiP;
        this.list_dokter = new ArrayList<>();
        this.list_pertemuan = new ArrayList<>();
        this.tempNewDokter = new ArrayList<>();
        this.tempNewPertemuan = new ArrayList<>();
        this.tempDeleteDokter = new ArrayList<>();
        this.tempDeletePertemuan = new ArrayList<>();
    }

    public void addDokter(Dokter newDokter){
        this.list_dokter.add(newDokter);
        this.tempNewDokter.add(newDokter);
        this.uiD.updateListDokter(this.list_dokter);
    }

    public void deleteDokter(int position){
        this.list_dokter.remove(position);
        this.uiD.updateListDokter(this.list_dokter);
    }

    public void addPertemuan(Pertemuan newPertemuan){
        this.list_pertemuan.add(newPertemuan);
        this.tempNewPertemuan.add(newPertemuan);
        this.uiP.updateListPertemuan(this.list_pertemuan);
    }

    public void deletePertemuan(int position){
        this.list_pertemuan.remove(position);
        this.uiP.updateListPertemuan(this.list_pertemuan);
    }

}
