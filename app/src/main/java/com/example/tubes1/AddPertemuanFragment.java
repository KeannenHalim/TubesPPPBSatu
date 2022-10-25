package com.example.tubes1;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;

import com.example.tubes1.databinding.FragmentPertemuanBinding;

import java.util.ArrayList;
import java.util.List;

public class AddPertemuanFragment extends Fragment implements IDokterDropdown{
    private FragmentPertemuanBinding binding;
    private Presenter presenter;
    private List<String> dokter;
    private ArrayAdapter<String> adapter;
    private Activity activity;
    private AddPertemuanFragment(){}
    public static AddPertemuanFragment newInstance(Presenter presenter, Activity activity){
        AddPertemuanFragment fragment = new AddPertemuanFragment();
        fragment.presenter = presenter;
        fragment.activity = activity;
        fragment.dokter = new ArrayList<>();
        fragment.adapter = new ArrayAdapter<String>(fragment.activity,android.R.layout.simple_list_item_1,fragment.dokter);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.binding = FragmentPertemuanBinding.inflate(inflater);
        View view = this.binding.getRoot();
        this.binding.btnCalendar.setOnClickListener(this::showDatePicker);
        this.binding.btnClock.setOnClickListener(this::showTimePicker);
        this.binding.btnSimpan.setOnClickListener(this::getForm);
        AutoCompleteTextView textView = (AutoCompleteTextView) this.binding.choice;
        textView.setAdapter(adapter);
        this.presenter.loadDokterDropdown();
        FragmentManager fm = getParentFragmentManager();
        fm.setFragmentResultListener("Datadate", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                String date = result.getString("date");
                showEtTanggal(date);
            }
        });

        fm.setFragmentResultListener("Datatime", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                String time = result.getString("time");
                showEtWaktu(time);
            }
        });
        return view;
    }

    private void getForm(View view) {
        String namaDokter = this.binding.choice.getText().toString();
        String namaPasien = this.binding.namaPasien.getText().toString();
        String keluhan = this.binding.keluhan.getText().toString();
        String tanggal = this.binding.etIsiTanggal.getText().toString();
        String waktu = this.binding.etIsiWaktu.getText().toString();
        Pertemuan temp = new Pertemuan(namaDokter,namaPasien,keluhan,tanggal,waktu,0,false);
        this.presenter.addPertemuan(temp);
        FragmentManager fm = getParentFragmentManager();
        Bundle bundle = new Bundle();
        bundle.putInt("page",2);
        fm.setFragmentResult("changePage",bundle);
    }

    private void showTimePicker(View view) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getParentFragmentManager(),"timePicker");
    }

    private void showDatePicker(View view) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getParentFragmentManager(), "datePicker");
    }

    public void showEtTanggal(String date){
        this.binding.etIsiTanggal.setText(date);
    }

    public void showEtWaktu(String date){
        this.binding.etIsiWaktu.setText(date);
    }



    public void update(List<Dokter> dokters){
        this.adapter.clear();
        for(int i = 0; i < dokters.size(); i++){
            this.dokter.add(dokters.get(i).getNama());
        }
        this.adapter.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        super.onResume();
        this.presenter.loadDokterDropdown();
    }


    @Override
    public void updateDropdown(List<Dokter> dokters) {
        this.update(dokters);
    }
}
