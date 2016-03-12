package com.example.grisha.calanderideahw;


import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;



import java.util.ArrayList;
import java.util.List;

/**
 * Created by Grisha on 3/10/2016.
 */
public class DayFragment extends Fragment {

    Fragment addEventFrag ;
    Bundle dayBundle = new Bundle();
    int year, month, day ;
    int  i =0 ;
    public static ArrayList<Events> eventsDay ;
    public static CustomerAdapter eventDayAdapter;
    public ListView theDayList ;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.day_fragment, container, false);
        dayBundle= getArguments() ;
        year = getArguments().getInt("year");
        month = getArguments().getInt("month");
        day = getArguments().getInt("day");
        TextView theDate = (TextView) view.findViewById(R.id.topDate);
        theDate.setText("Date: " + month +"/" + day + "/" + year);
        eventsDay = new ArrayList<>();



        // for loop gave issues with unexpected error of ; right after )


        while ( i < MainActivity.eventsDateMain.size() ){
            Events obj = MainActivity.eventsDateMain.get(i);
            if( obj.getYear() == year && obj.getMonth() == month && obj.getDay() == day){
                eventsDay.add(obj);
            }
            i++ ;
        }



        eventDayAdapter = new CustomerAdapter (getActivity(), eventsDay);
        theDayList  = (ListView) view.findViewById(R.id.eventListView);
        theDayList.setAdapter(eventDayAdapter);

        Button btn = (Button) view.findViewById(R.id.addEvents);
        Button backBtn= (Button) view.findViewById(R.id.BackToCalendar);
        theDayList .setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long arg) {
                eventsDay.remove(position);
                MainActivity.eventAdapter.notifyDataSetChanged();
                eventDayAdapter.notifyDataSetChanged();
            }
        });

        btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        createEvent();
                    }
                }
        );
        backBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getFragmentManager().popBackStackImmediate();
                    }
                }
        );

        theDayList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                                             @Override
                                                             public void onItemClick(AdapterView<?> parent, View view, int position, long arg) {
                                                                 eventsDay.remove(position);
                                                                 eventDayAdapter.notifyDataSetChanged();

                                                             }
                                                         }
        ) ;






        return view;

    }


    public void createEvent(){

        addEventFrag = new CreateEventFrag() ;
        addEventFrag.setArguments(dayBundle);
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_list_main, addEventFrag)
                .addToBackStack(null)
                .commit();
    }




}
