
package com.example.grisha.calanderideahw;


import android.content.Context;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.BaseAdapter;
        import android.widget.Button;
        import android.widget.TextView;




        import java.util.ArrayList;


public class CustomerAdapter extends BaseAdapter {
    private ArrayList<Events> Event;
    private Context context;
    private String thesetittle ;

    // A cache for looking up Views
    private static class ViewHolder {
        TextView name;
        Button delete;
        TextView time ;
    }

    public CustomerAdapter(Context context, ArrayList<Events> event) {
        this.Event= event;
        this.context = context;
    }

    @Override
    public int getCount() {
        return Event.size();
    }

    @Override
    public Events getItem(int position) {
        return Event.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.row_xml_layout, parent, false);

            viewHolder.name = (TextView) convertView.findViewById(R.id.rowTittle);
            viewHolder.time = (TextView) convertView.findViewById(R.id.rowtime);

            //TODO initialize the other UI elements

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // Get the data item for this position
        final Events events = getItem(position);
        String minutes ;
        if(events.getMinute() < 10 ){
            minutes = ":0" + events.getMinute() ;
        }else{
            minutes = ":" + events.getMinute();
        }



        //TODO Populate the data into the template view using the data object
        viewHolder.name.setText(events.getTittle() );
        viewHolder.time.setText(events.getHour() + minutes);

        //TODO implement onclick for delete (do this after implementing retrofit)


        // Return the completed view to render on screen
        return convertView;
    }


}
