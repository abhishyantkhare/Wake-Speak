package com.katgstudios.abhishyant.wakespeak;

import android.os.Bundle;
import android.support.v4.app.Fragment;
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
    private ArrayAdapter mAdapter;
    public AlarmFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mAdapter = new ArrayAdapter(getActivity(),R.layout.alarm_list_items,R.id.alarm_list_textview,new ArrayList());
        View rootView = inflater.inflate(R.layout.fragment_alarm, container, false);
        ListView alarmList = (ListView) rootView.findViewById(R.id.alarm_list);

        alarmList.setAdapter(mAdapter);


        return rootView;
    }
}

