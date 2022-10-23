package com.example.tubes1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.tubes1.databinding.FragmentDaftarDokterBinding;

import java.util.List;

public class DaftarDokterFragment extends Fragment implements IDokter{
    private FragmentDaftarDokterBinding binding;
    private Presenter presenter;
    private DokterListAdapter adapter;
    private DaftarDokterFragment(){}
    public static DaftarDokterFragment newInstance(Presenter presenter){
        DaftarDokterFragment fragment = new DaftarDokterFragment();
        fragment.presenter = presenter;
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.binding = FragmentDaftarDokterBinding.inflate(inflater);
        View view = this.binding.getRoot();
        this.binding.floatingActionButton.setOnClickListener(this::onClick);
        this.adapter = new DokterListAdapter(getActivity(),getParentFragmentManager(), this.presenter);
        this.binding.lstDokter.setAdapter(adapter);
        this.presenter.loadDokter();
        return view;
    }

    private void onClick(View view) {
        AddDokterDialogFragment fragment = AddDokterDialogFragment.newInstance(this.presenter);
        fragment.show(this.getParentFragmentManager().beginTransaction(),"dialog");
    }

    @Override
    public void updateListDokter(List<Dokter> dokters) {
        this.adapter.update(dokters);
    }

    @Override
    public void onResume() {
        super.onResume();
        this.presenter.loadDokter();
    }
}


