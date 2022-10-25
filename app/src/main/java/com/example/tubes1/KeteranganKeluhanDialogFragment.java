package com.example.tubes1;

import android.app.Dialog;
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
    private String keluhan;
    private int idx;
    private Presenter presenter;
    private KeteranganKeluhanDialogFragment(){}

    public static KeteranganKeluhanDialogFragment newInstance(String keluhan, int idx, Presenter presenter){
        KeteranganKeluhanDialogFragment fragment = new KeteranganKeluhanDialogFragment();
        fragment.keluhan = keluhan;
        fragment.idx = idx;
        fragment.presenter = presenter;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.binding = FragmentKeteranganKeluhanDialogBinding.inflate(inflater);
        View view = this.binding.getRoot();
        this.setKeluhan(this.keluhan);
        this.binding.btnDismiss.setOnClickListener(this::onClick);
        this.binding.btnMarkAsDone.setOnClickListener(this::onClickMark);
        return view;
    }

    private void onClickMark(View view) {
        this.presenter.toggleStatus(this.idx);
        dismiss();
    }

    private void onClick(View view) {
        dismiss();
    }

    public void setKeluhan(String keluhan){
        this.binding.tvKeluhan.setText(keluhan);
    }
}
