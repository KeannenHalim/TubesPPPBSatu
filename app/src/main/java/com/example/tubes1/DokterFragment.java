package com.example.tubes1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.tubes1.databinding.FragmentDokterBinding;

public class DokterFragment extends Fragment {
    private FragmentDokterBinding binding;

    private DokterFragment(){}
    public static DokterFragment newInstance(){
        DokterFragment fragment = new DokterFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.binding = FragmentDokterBinding.inflate(inflater);
        View view = this.binding.getRoot();
        return view;
    }
}
