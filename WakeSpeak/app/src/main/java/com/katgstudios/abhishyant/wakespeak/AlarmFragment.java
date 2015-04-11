package com.katgstudios.abhishyant.wakespeak;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by abhishyant on 4/5/15.
 */
public class AlarmFragment extends Fragment{
    public static ArrayAdapter mAdapter;
    private static ListView mAlarmList;
    public AlarmFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        mAdapter = new ArrayAdapter(getActivity(),R.layout.alarm_list_items,R.id.alarm_list_textview,new ArrayList());
        View rootView = inflater.inflate(R.layout.fragment_alarm, container, false);
        mAlarmList = (ListView) rootView.findViewById(R.id.alarm_list);

        mAlarmList.setAdapter(mAdapter);
        setAlarmDisplay();




        return rootView;
    }
    public static void setAlarmDisplay(){

       // Log.d("ALARMFRAGMENT","CALLED");
        //Log.d("ALARMLIST",newAlarmList.get(0));

        mAdapter.clear();
        mAdapter.addAll(AlarmActivity.mAlarmData);
       // Log.d("ADAPTER",mAdapter.getItem(0).toString());
        mAlarmList.setAdapter(mAdapter);


    }
}

