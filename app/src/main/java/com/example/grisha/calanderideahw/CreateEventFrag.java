package com.example.grisha.calanderideahw;


import android.app.Fragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by Grisha on 3/10/2016.
 */
public class CreateEventFrag extends Fragment {
    Button btnTimePicker, btnConfirm, btnCancel ;
    EditText edTittle ;
    int mYear, mMonth, mDay, mHour, mMinute;
    String finsihTittle ;
    MainActivity save  = new MainActivity();
    Events setEvent ;
    String theTittle ;

    int getPickerHour , getPickerMinute ;
    TimePicker timeNow ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.create_event, container, false);
        timeNow = (TimePicker) view.findViewById(R.id.timePicker);
        mYear = getArguments().getInt("year");
        mMonth = getArguments().getInt("month");
        mDay = getArguments().getInt("day");
        TextView theDate = (TextView) view.findViewById(R.id.topDate);
        //theDate.setText("Date: " + mMonth +"/" + mDay + "/" + mYear);
        edTittle = (EditText) view.findViewById(R.id.tittle);

        edTittle.setOnKeyListener(new EditText.OnKeyListener(){
            public boolean onKey(View v , int keyCode, KeyEvent e){
                //edTittle = (EditText) v.findViewById(R.id.tittle);
                //CustomerAdapter.thesetittle  = edTittle.getText().toString() ;

                return false;
            }
        });

        btnConfirm = (Button) view.findViewById(R.id.submitButton);
        btnCancel = (Button) view.findViewById(R.id.theCancelButton);


        timeNow.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            public void onTimeChanged(TimePicker arg0, int arg1, int arg2) {
                edTittle = (EditText) getActivity().findViewById(R.id.tittle);
                theTittle  = edTittle.getText().toString();
                getPickerHour = arg0.getCurrentHour();
                getPickerMinute = arg0.getCurrentMinute() ;
            }
        });







        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                confirmEvent(mYear, mMonth, mDay, getPickerHour, getPickerMinute,theTittle );
            }
        });






        return view ;
    }




    void confirmEvent( int year, int month, int day , int hour, int minute, String tittle){
        if(mYear == 0 || tittle  == null){
            Toast.makeText(getActivity(), "Invalid Date or No Tittle", Toast.LENGTH_LONG).show();
        }
        else {

            setEvent = new Events(year,month,day,hour,minute,tittle);


                MainActivity.eventsDateMain.add(setEvent);

            DayFragment.eventDayAdapter.notifyDataSetChanged();
            save.writeTofile();


            getFragmentManager().popBackStackImmediate();
        }


    }

    void declineEvent(){
        getFragmentManager().popBackStackImmediate();

    }

}