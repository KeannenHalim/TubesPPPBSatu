package com.example.tubes1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;

import com.example.tubes1.databinding.FragmentPertemuanBinding;

public class PertemuanFragment extends Fragment {
    private FragmentPertemuanBinding binding;
    private PertemuanFragment(){}
    public static PertemuanFragment newInstance(){
        PertemuanFragment fragment = new PertemuanFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.binding = FragmentPertemuanBinding.inflate(inflater);
        View view = this.binding.getRoot();
        this.binding.btnCalendar.setOnClickListener(this::showDatePicker);
        this.binding.btnClock.setOnClickListener(this::showTimePicker);
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
}
