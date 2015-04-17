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
public class AlarmListAdapter extends ArrayAdapter<AlarmObject> {
      Context context;
    int layoutResourceId;
     ArrayList<AlarmObject> alarmObjectArrayList;

    public AlarmListAdapter(Context _context,ArrayList<AlarmObject> data){
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
        if(alarmObjectArrayList.size()>1)
        Log.d("ADAPTER","stuff");
        alarmName.setText(alarmObjectArrayList.get(position).getAlarmName());
        alarmTime.setText(alarmObjectArrayList.get(position).formatTime());
        return rowView;

    }
}
