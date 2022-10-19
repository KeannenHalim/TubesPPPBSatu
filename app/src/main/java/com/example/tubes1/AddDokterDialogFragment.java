package com.example.tubes1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.tubes1.databinding.FragmentAddDokterDialogBinding;

public class AddDokterDialogFragment extends DialogFragment {
    private FragmentAddDokterDialogBinding binding;

    private AddDokterDialogFragment(){}
    private Presenter presenter;
    public static AddDokterDialogFragment newInstance(Presenter presenter){
        AddDokterDialogFragment fragment = new AddDokterDialogFragment();
        fragment.presenter = presenter;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.binding = FragmentAddDokterDialogBinding.inflate(inflater);
        View view = this.binding.getRoot();
        this.binding.btnAdd.setOnClickListener(this::onClickAdd);
        this.binding.btnCancel.setOnClickListener(this::onClickCancel);
        return view;
    }

    private void onClickCancel(View view) {
        dismiss();
    }

    private void onClickAdd(View view) {
        String nama = this.binding.etIsiNama.getText().toString();
        String spesialis = this.binding.etIsiSpesialis.getText().toString();
        String noTelp = this.binding.etIsiTelepon.getText().toString();
        Dokter temp = new Dokter(nama,spesialis,noTelp,0);
        this.presenter.addDokter(temp);
        dismiss();
    }
}
