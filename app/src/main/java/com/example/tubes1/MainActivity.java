package com.example.tubes1;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

import com.example.tubes1.databinding.ActivityMainBinding;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
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
                .commit();
    }
}