package com.katgstudios.abhishyant.wakespeak;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by abhishyant on 4/16/15.
 */
public class AlarmListAdapter extends ArrayAdapter<String> {
      Context context;
    int layoutResourceId;
     ArrayList<String> alarmObjectArrayList = new ArrayList<>();

    public AlarmListAdapter(Context _context,ArrayList<String> data){
        super(_context,R.layout.alarm_list_items,data);
        context=_context;
        alarmObjectArrayList = data;
    }
    @Override
    public View getView(int position, View convertView,ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.alarm_list_items,parent,false);
        TextView alarmName = (TextView)rowView.findViewById(R.id.alarm_name);
        TextView alarmTime = (TextView)rowView.findViewById(R.id.alarm_time);
        TextView days = (TextView) rowView.findViewById(R.id.days);
        int spcIndx = timeIndex(alarmObjectArrayList.get(position))-1;
        String nameanddays = alarmObjectArrayList.get(position).substring(spcIndx+1);
        Log.d("name and days",nameanddays);
        int daysIndx = nameanddays.indexOf(" ");
        Log.d("ADAPTER",Integer.toString(daysIndx));

        alarmName.setText(alarmObjectArrayList.get(position).substring(0,spcIndx));
        Log.d("name",alarmName.getText().toString());
        alarmTime.setText(nameanddays.substring(0,daysIndx));
        Log.d("time",alarmTime.getText().toString());
        days.setText(nameanddays.substring(daysIndx+1));
        Log.d("days",days.getText().toString());
        return rowView;

    }
    public boolean isNumeric(String str)
    {
        return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }
    public int timeIndex(String str){
        for(int i = 0; i < str.length();i++){
            if(isNumeric(str.substring(i,i+1)))
                return i;
        }
        return str.length()-1;
    }
}
