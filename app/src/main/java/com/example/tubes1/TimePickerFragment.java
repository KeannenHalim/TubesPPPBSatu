package com.example.tubes1;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TimePicker;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import java.util.Calendar;

public class TimePickerFragment extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        String jam = Integer.toString(hourOfDay);
        if(hourOfDay < 10){
            jam = "0"+jam;
        }

        String menit = Integer.toString(minute);
        if(minute < 10){
            menit = "0"+menit;
        }
        String time = jam +":"+menit;
        Bundle bundle = new Bundle();
        bundle.putString("time",time);
        FragmentManager fm = getParentFragmentManager();
        fm.setFragmentResult("Datatime",bundle);
    }
}
