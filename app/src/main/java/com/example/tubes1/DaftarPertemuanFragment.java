package com.example.tubes1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.tubes1.databinding.FragmentDaftarDokterBinding;
import com.example.tubes1.databinding.FragmentDaftarPertemuanBinding;

import java.util.List;

public class DaftarPertemuanFragment extends Fragment implements IPertemuan {
    private FragmentDaftarPertemuanBinding binding;
    private Presenter presenter;
    private PertemuanListAdapter adapter;
    private DaftarPertemuanFragment(){}
    public static DaftarPertemuanFragment newInstance(Presenter presenter){
        DaftarPertemuanFragment fragment = new DaftarPertemuanFragment();
        fragment.presenter = presenter;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.binding = FragmentDaftarPertemuanBinding.inflate(inflater);
        View view = this.binding.getRoot();
        this.adapter = new PertemuanListAdapter(getActivity(),getParentFragmentManager(), this.presenter);
        this.binding.lstPertemuan.setAdapter(adapter);
        this.binding.floatingActionButton.setOnClickListener(this::onClickAdd);
        this.presenter.loadPertemuan();
        return view;
    }

    private void onClickAdd(View view) {
        Bundle bundle = new Bundle();
        bundle.putInt("page",5);
        FragmentManager fm = getParentFragmentManager();
        fm.setFragmentResult("changePage",bundle);
    }

    @Override
    public void onResume() {
        super.onResume();
        this.presenter.loadPertemuan();
    }

    @Override
    public void updateListPertemuan(List<Pertemuan> pertemuans) {
        this.adapter.update(pertemuans);
    }
}
