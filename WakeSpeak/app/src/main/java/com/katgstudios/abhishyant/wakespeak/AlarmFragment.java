package com.katgstudios.abhishyant.wakespeak;

import android.app.ActionBar;
import android.graphics.drawable.ColorDrawable;
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
    public static AlarmListAdapter mAdapter;
    private static ListView mAlarmList;
    public AlarmFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        mAdapter = new AlarmListAdapter(getActivity(),new ArrayList<AlarmObject>());
        View rootView = inflater.inflate(R.layout.fragment_alarm, container, false);
        mAlarmList = (ListView) rootView.findViewById(R.id.alarm_list);
        ParseUser curentUser = ParseUser.getCurrentUser();
        if(curentUser.has("test1")){
            Log.d("CURRENT USER","HAS TEST");
        }
        if(curentUser.has("AlarmList")){
            Toast.makeText(getActivity(), "HAS ALARMS", Toast.LENGTH_LONG).show();
            ArrayList<AlarmObject> alarmObjects = (ArrayList<AlarmObject>)curentUser.get("AlarmList");
            mAdapter.addAll(alarmObjects);
            Log.d("ALARM FRAGMENT CHECK", alarmObjects.get(0).formatTime());

        }
        mAlarmList.setAdapter(mAdapter);





        return rootView;
    }

}

