package com.example.tubes1;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.DatePicker;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        // Do something with the date chosen by the user
        Bundle bundle = new Bundle();
        String hari = Integer.toString(day);
        String bulan = Integer.toString(month+1);
        String tahun = Integer.toString(year);
        if(month+1 < 10){
            bulan = "0"+bulan;
        }

        if(day < 10){
            hari = "0"+hari;
        }

        String date = hari +"/"+ bulan +"/"+tahun;
        bundle.putString("date", date);
        FragmentManager fm = getParentFragmentManager();
        fm.setFragmentResult("Datadate",bundle);
    }

}
