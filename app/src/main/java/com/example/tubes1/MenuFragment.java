package com.example.tubes1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.tubes1.databinding.FragmentMenuBinding;

public class MenuFragment extends Fragment {
    private FragmentMenuBinding binding;
    private MenuListAdapter adapter;
    public MenuFragment(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.binding = FragmentMenuBinding.inflate(inflater);
        View view = this.binding.getRoot();
        this.adapter = new MenuListAdapter(getActivity(),getParentFragmentManager());
        this.adapter.addLine(DaftarMenu.menu);
        this.binding.listView.setAdapter(this.adapter);
        return view;
    }
}
