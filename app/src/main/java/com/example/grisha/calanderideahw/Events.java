package com.example.grisha.calanderideahw;


import java.io.Serializable;

/**
 * Created by Grisha on 3/9/2016.
 */
public class Events implements Serializable{

    private int year, month, day, hour, minute ;

    private String eventName ;


    public  Events(int year, int month, int day, int hour, int minute, String event){
        this.year = year ;
        this.month = month ;
        this.day = day ;
        this.hour = hour ;
        this.minute = minute ;
        this.eventName = event ;

    }
    public int getYear(){
        return year ;
    }
    public int getMonth(){
        return month ;
    }

    public int getDay() {
        return day;
    }
    public int getHour(){
        return hour ;
    }

    public int getMinute() {
        return minute;
    }

    public String getTittle(){
        return eventName ;
    }



    @Override
    public String toString() {
        return eventName + ": " + month +"/" + day +"/" + year + " at" + hour +":" + minute ;
    }

}
