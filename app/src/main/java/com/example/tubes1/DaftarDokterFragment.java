package com.example.tubes1;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.tubes1.databinding.FragmentDaftarDokterBinding;

import java.util.List;

public class DaftarDokterFragment extends Fragment implements IDokter {
    private FragmentDaftarDokterBinding binding;
    private Presenter presenter;
    private DokterListAdapter adapter;

    private DaftarDokterFragment() {
    }

    public static DaftarDokterFragment newInstance(Presenter presenter) {
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
        this.adapter = new DokterListAdapter(getActivity(), getParentFragmentManager(), this.presenter);
        this.binding.lstDokter.setAdapter(adapter);
        this.presenter.loadDokter();
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.search_menu, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = new SearchView(((MainActivity) getContext()).getSupportActionBar().getThemedContext());
        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        item.setActionView(searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                presenter.loadDokterFilter(newText);
                return false;
            }
        });
    }

    private void onClick(View view) {
        AddDokterDialogFragment fragment = AddDokterDialogFragment.newInstance(this.presenter);
        fragment.show(this.getParentFragmentManager().beginTransaction(), "dialog");
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


