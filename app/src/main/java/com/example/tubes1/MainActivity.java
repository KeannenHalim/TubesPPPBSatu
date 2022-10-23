package com.example.tubes1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.example.tubes1.databinding.ActivityMainBinding;

import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IDokter,IPertemuan,IDokterDropdown {
    private ActivityMainBinding binding;
    private HashMap<String, Fragment> mp;
    private FragmentManager fm;
    private Toolbar toolbar;
    private Presenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = this.binding.getRoot();
        setContentView(view);
        this.presenter = new Presenter(this,this, this,this);
        this.mp = new HashMap<>();
        this.mp.put("HomeFragment", HomeFragment.newInstance());
        this.mp.put("DaftarDokterFragment", DaftarDokterFragment.newInstance(this.presenter));
        this.mp.put("PertemuanFragment", PertemuanFragment.newInstance(this.presenter,this));
        this.mp.put("DaftarPertemuanFragment", DaftarPertemuanFragment.newInstance(this.presenter));

        //set toolbar
        this.toolbar = this.binding.toolbar;
        this.setSupportActionBar(toolbar);

        //tombol garis tiga
        ActionBarDrawerToggle abdt = new ActionBarDrawerToggle(this,this.binding.drawerLayout,this.toolbar,R.string.open_drawer,R.string.close_drawer);
        this.binding.drawerLayout.addDrawerListener(abdt);
        abdt.syncState();

        //fragment manager
        this.fm = this.getSupportFragmentManager();
        FragmentTransaction ft = this.fm.beginTransaction();
        ft.add(this.binding.fragmentContainer.getId(),this.mp.get("HomeFragment"))
                .addToBackStack(null)
                .commit();

        this.fm.setFragmentResultListener(
                "changePage", this, new FragmentResultListener() {
                    @Override
                    public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                        int page = result.getInt("page");
                        changePage(page);
                    }
                });
    }

    private void changePage(int page){
        this.binding.drawerLayout.closeDrawers();
        FragmentTransaction ft = this.fm.beginTransaction();
        if(page == 1){
            if(this.mp.get("HomeFragment").isAdded()){
                ft.show(this.mp.get("HomeFragment"));
            }else{
                ft.add(this.binding.fragmentContainer.getId(), this.mp.get("HomeFragment"))
                        .addToBackStack(null);
            }

            if(this.mp.get("DaftarDokterFragment").isAdded()){
                ft.hide(this.mp.get("DaftarDokterFragment"));
            }

            if(this.mp.get("PertemuanFragment").isAdded()){
                ft.hide(this.mp.get("PertemuanFragment"));
            }

            if(this.mp.get("DaftarPertemuanFragment").isAdded()){
                ft.hide(this.mp.get("DaftarPertemuanFragment"));
            }
        }else if(page == 2){
            if(this.mp.get("DaftarPertemuanFragment").isAdded()){
                ft.show(this.mp.get("DaftarPertemuanFragment"));
            }else{
                ft.add(this.binding.fragmentContainer.getId(), this.mp.get("DaftarPertemuanFragment"))
                        .addToBackStack(null);
            }

            if(this.mp.get("HomeFragment").isAdded()){
                ft.hide(this.mp.get("HomeFragment"));
            }

            if(this.mp.get("DaftarDokterFragment").isAdded()){
                ft.hide(this.mp.get("DaftarDokterFragment"));
            }

            if(this.mp.get("PertemuanFragment").isAdded()){
                ft.hide(this.mp.get("PertemuanFragment"));
            }
        }else if(page == 3){
            if(this.mp.get("DaftarDokterFragment").isAdded()){
                ft.show(this.mp.get("DaftarDokterFragment"));
            }else{
                ft.add(this.binding.fragmentContainer.getId(), this.mp.get("DaftarDokterFragment"))
                        .addToBackStack(null);
            }

            if(this.mp.get("HomeFragment").isAdded()){
                ft.hide(this.mp.get("HomeFragment"));
            }

            if(this.mp.get("DaftarPertemuanFragment").isAdded()){
                ft.hide(this.mp.get("DaftarPertemuanFragment"));
            }

            if(this.mp.get("PertemuanFragment").isAdded()){
                ft.hide(this.mp.get("PertemuanFragment"));
            }

        }else if(page == 5){
            if(this.mp.get("PertemuanFragment").isAdded()){
                ft.show(this.mp.get("PertemuanFragment"));
            }else{
                ft.add(this.binding.fragmentContainer.getId(), this.mp.get("PertemuanFragment"))
                        .addToBackStack(null);
            }

            if(this.mp.get("HomeFragment").isAdded()){
                ft.hide(this.mp.get("HomeFragment"));
            }

            if(this.mp.get("DaftarPertemuanFragment").isAdded()){
                ft.hide(this.mp.get("DaftarPertemuanFragment"));
            }

            if(this.mp.get("DaftarDokterFragment").isAdded()){
                ft.hide(this.mp.get("DaftarDokterFragment"));
            }
        }
        ft.commit();

        if(page == 4){
            this.closeApplication();
        }
    }

    private void closeApplication(){
        this.moveTaskToBack(true);
        this.finish();
    }

    @Override
    public void updateListDokter(List<Dokter> dokters) {
       DaftarDokterFragment fragment = (DaftarDokterFragment) this.mp.get("DaftarDokterFragment");
       fragment.updateListDokter(dokters);
    }

    @Override
    public void updateListPertemuan(List<Pertemuan> pertemuans) {
        DaftarPertemuanFragment fragment = (DaftarPertemuanFragment) this.mp.get("DaftarPertemuanFragment");
        fragment.updateListPertemuan(pertemuans);
    }


    @Override
    public void updateDropdown(List<Dokter> dokters) {
        PertemuanFragment fragment = (PertemuanFragment) this.mp.get("PertemuanFragment");
        fragment.updateDropdown(dokters);
    }
}