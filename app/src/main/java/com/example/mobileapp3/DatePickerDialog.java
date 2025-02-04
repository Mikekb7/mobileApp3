package com.example.mobileapp3;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.view.LayoutInflater;
import java.util.Calendar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.DialogFragment;

public class DatePickerDialog extends DialogFragment{
    Calendar selectedDate;

    public interface SaveDateListener {
        void didFinishDatePickerDialog(Calendar selectedTime);
    }
    public DatePickerDialog(){
                // Empty constructor required for DialogFragment
            }
            @Override
            public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
                final View view = inflater.inflate(R.layout.select_date, container);

                getDialog().setTitle("Select Date");
                selectedDate = Calendar.getInstance();

                final CalendarView cv = view.findViewById(R.id.calendarView);
                cv.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                    @Override
                    public void onSelectedDayChange(CalendarView calendarView, int year, int month, int day) {
                        selectedDate.set(year, month, day);
                    }
                });

                Button saveButton = view.findViewById(R.id.buttonSelect);
                saveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        saveItem(selectedDate);
                    }
                });

                Button canceButton = view.findViewById(R.id.buttonCancel);
                canceButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        getDialog().dismiss();
                    }
                });


                return view;
            }
            private void saveItem (Calendar selectedItem){
                SaveDateListener activity = (SaveDateListener) getActivity();
                activity.didFinishDatePickerDialog(selectedItem);
                getDialog().dismiss();

                }
            }


