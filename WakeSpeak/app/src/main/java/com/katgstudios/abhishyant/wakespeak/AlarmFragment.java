package com.katgstudios.abhishyant.wakespeak;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.ParseUser;

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
        ParseUser curentUser = ParseUser.getCurrentUser();
        if(curentUser.has("test1")){
            Log.d("CURRENT USER","HAS TEST");
        }
        if(curentUser.has("AlarmNames")){
            Toast.makeText(getActivity(), "HAS ALARMS", Toast.LENGTH_LONG).show();
            ArrayList<String> alarmNames = (ArrayList<String>)curentUser.get("AlarmNames");
            mAdapter.addAll(alarmNames);
            Log.d("ALARM FRAGMENT CHECK", "has alarm names");

        }
        mAlarmList.setAdapter(mAdapter);
        //setAlarmDisplay();




        return rootView;
    }
    public static void setAlarmDisplay(){

       // Log.d("ALARMFRAGMENT","CALLED");
        //Log.d("ALARMLIST",newAlarmList.get(0));

        mAdapter.clear();
        mAdapter.addAll(AlarmActivity.mAlarmNames);
       // Log.d("ADAPTER",mAdapter.getItem(0).toString());
        mAlarmList.setAdapter(mAdapter);


    }
}

