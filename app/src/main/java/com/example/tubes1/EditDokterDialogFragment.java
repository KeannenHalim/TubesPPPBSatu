package com.example.tubes1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.tubes1.databinding.FragmentEditDokterDialogBinding;

public class EditDokterDialogFragment extends DialogFragment {
    private FragmentEditDokterDialogBinding binding;
    private Presenter presenter;
    private String nama;
    private String spesialis;
    private String telepon;
    private int idx;
    private EditDokterDialogFragment(){}
    public static EditDokterDialogFragment newInstance(Presenter presenter, String nama, String spesialis, String telepon, int idx){
        EditDokterDialogFragment fragment = new EditDokterDialogFragment();
        fragment.presenter = presenter;
        fragment.nama = nama;
        fragment.spesialis = spesialis;
        fragment.telepon = telepon;
        fragment.idx = idx;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.binding = FragmentEditDokterDialogBinding.inflate(inflater);
        View view = this.binding.getRoot();
        this.setView();
        this.binding.btnSave.setOnClickListener(this::onClickSave);
        this.binding.btnCancel.setOnClickListener(this::onClickCancel);
        return view;
    }

    private void onClickSave(View view) {
        this.presenter.updateDokter(this.idx,this.binding.etIsiNama.getText().toString(), this.binding.etIsiSpesialis.getText().toString(), this.binding.etIsiTelepon.getText().toString());
        dismiss();
    }

    private void onClickCancel(View view) {
        dismiss();
    }

    private void setView(){
        this.binding.etIsiNama.setText(this.nama);
        this.binding.etIsiSpesialis.setText(this.spesialis);
        this.binding.etIsiTelepon.setText(this.telepon);
    }

}
