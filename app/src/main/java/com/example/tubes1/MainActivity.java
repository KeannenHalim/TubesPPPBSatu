package com.example.tubes1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

import com.example.tubes1.databinding.ActivityMainBinding;

import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IDokter,IPertemuan {
    private ActivityMainBinding binding;
    private HashMap<String, Fragment> mp;
    private FragmentManager fm;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = this.binding.getRoot();
        setContentView(view);

        this.mp = new HashMap<>();
        this.mp.put("HomeFragment", HomeFragment.newInstance());
        this.mp.put("DokterFragment", DokterFragment.newInstance());
        this.mp.put("PertemuanFragment", PertemuanFragment.newInstance());
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
//        ft.add(this.binding.fragmentContainer.getId(),this.mp.get("HomeFragment"))
        ft.add(this.binding.fragmentContainer.getId(),this.mp.get("PertemuanFragment"))
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
                        .addToBackStack("Home");
            }

//            if(this.mp.get("DokterFragment").isAdded()){
//                ft.hide(this.mp.get("DokterFragment"));
//            }
//
//
//            if(this.mp.get("PertemuanFragment").isAdded()){
//                ft.hide(this.mp.get("PertemuanFragment"));
//            }
        }else if(page == 2){

        }else if(page == 3){

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

    }

    @Override
    public void updateListPertemuan(List<Pertemuan> pertemuans) {

    }
}