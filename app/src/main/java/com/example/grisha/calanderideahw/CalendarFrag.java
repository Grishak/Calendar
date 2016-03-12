package com.example.grisha.calanderideahw;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class CalendarFrag extends Fragment {


   FrameLayout relative;
    CalendarView calendar;
    Bundle bundle = new Bundle();
    ArrayList dateEvents = new ArrayList<>();
    Fragment dayFragment ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate( R.layout.fragment_calendar, container, false);

        relative= (FrameLayout) view.findViewById(R.id.relative);

        calendar = new CalendarView(getActivity());

        FrameLayout.LayoutParams params =
                new FrameLayout.LayoutParams((int)
                        RelativeLayout.LayoutParams.MATCH_PARENT, (int) RelativeLayout.LayoutParams.MATCH_PARENT);
        params.topMargin = 100;
        calendar.setLayoutParams(params);

        relative.addView(calendar);

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view,
                                            int year, int month, int day) {

                bundle.putInt("year", year);
                bundle.putInt("month", month);
                bundle.putInt("day", day);
                dateViewCreate(bundle);





            }
        });
        return  view ;
    }
    void dateViewCreate(Bundle bnd){

        dayFragment = new DayFragment() ;
        dayFragment.setArguments(bnd);
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_list_main, dayFragment)
                .addToBackStack(null)
                .commit();
    }
}
