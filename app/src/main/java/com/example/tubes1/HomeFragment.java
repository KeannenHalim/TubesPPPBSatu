package com.example.tubes1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.tubes1.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    private HomeFragment(){}
    public static HomeFragment newInstance(){
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.binding = FragmentHomeBinding.inflate(inflater);
        View view = this.binding.getRoot();
        this.binding.btnPertemuan.setOnClickListener(this::onClick);
        return view;
    }

    private void onClick(View view) {
        FragmentManager fm = getParentFragmentManager();
        Bundle bundle = new Bundle();
        bundle.putInt("page",5);
        fm.setFragmentResult("changePage",bundle);
    }
}
