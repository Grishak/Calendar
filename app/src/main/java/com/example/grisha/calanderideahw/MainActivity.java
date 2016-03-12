package com.example.grisha.calanderideahw;


        import android.app.Activity;

        import android.app.Fragment;
        import android.content.Context;
        import android.os.Bundle;
        import android.widget.ArrayAdapter;


        import java.io.File;
        import java.io.FileInputStream;
        import java.io.FileNotFoundException;
        import java.io.FileOutputStream;
        import java.io.IOException;
        import java.io.ObjectInputStream;
        import java.io.ObjectOutputStream;
        import java.util.ArrayList;

public class MainActivity extends Activity {

    Fragment calendarFrag ;
    public static ArrayList<Events> eventsDateMain ;
    public static ArrayAdapter<Events> eventAdapter;
    public static Context context;


 ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        readFromFile();





        eventsDateMain = new ArrayList<>();
        context = this.getApplicationContext();



        calendarFrag =  new CalendarFrag();
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_list_main, calendarFrag)
                .addToBackStack(null)
                .commit();

    }

    public void onPause() {
        super.onPause();
            writeTofile();
    }



    public void writeTofile(){


        FileOutputStream fileOutputStream;
        ObjectOutputStream ob ;

        try{
            File file = new File("GKCalendar");

            if(!file.exists()){
                file.createNewFile();

            }

            fileOutputStream = openFileOutput("GKCalendar", Context.MODE_PRIVATE);
            ob = new ObjectOutputStream(fileOutputStream);
            ob.writeObject(eventsDateMain);
            fileOutputStream.close();
        } catch (FileNotFoundException e ){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }



    }
    public  void readFromFile(){

        FileInputStream fileIn;
        ObjectInputStream obIn;
        try{
            fileIn = openFileInput("GKCalendar");
            obIn = new ObjectInputStream(fileIn);
            eventsDateMain =((ArrayList<Events> )obIn.readObject());
        }
        catch (FileNotFoundException e ){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }

    }
}
