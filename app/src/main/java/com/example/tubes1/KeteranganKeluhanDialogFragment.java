package com.example.tubes1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.tubes1.databinding.FragmentKeteranganKeluhanDialogBinding;

public class KeteranganKeluhanDialogFragment extends DialogFragment {
    private FragmentKeteranganKeluhanDialogBinding binding;
    private KeteranganKeluhanDialogFragment(){}

    public static KeteranganKeluhanDialogFragment newInstance(){
        KeteranganKeluhanDialogFragment fragment = new KeteranganKeluhanDialogFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.binding = FragmentKeteranganKeluhanDialogBinding.inflate(inflater);
        View view = this.binding.getRoot();
        this.binding.btnDismiss.setOnClickListener(this::onClick);
        return view;
    }

    private void onClick(View view) {
        dismiss();
    }

    public void setKeluhan(String keluhan){
        this.binding.tvKeluhan.setText(keluhan);
    }
}
