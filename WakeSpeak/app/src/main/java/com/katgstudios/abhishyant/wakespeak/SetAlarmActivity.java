package com.katgstudios.abhishyant.wakespeak;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.NumberPicker;


public class SetAlarmActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_alarm);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_set_alarm, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment implements NumberPicker.OnValueChangeListener {
        private  NumberPicker mHourPicker;
        private NumberPicker mMinutePicker;
        private NumberPicker mAMPMPicker;
        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_set_alarm, container, false);
            mHourPicker = (NumberPicker) rootView.findViewById(R.id.hourPicker);
            mHourPicker.setMinValue(1);
            mHourPicker.setMaxValue(12);
            mMinutePicker = (NumberPicker) rootView.findViewById(R.id.minutePicker);
            mMinutePicker.setMinValue(0);
            mMinutePicker.setMaxValue(59);
           mAMPMPicker = (NumberPicker) rootView.findViewById(R.id.AMPMPicker);


            NumberPicker.Formatter minFormatter = new NumberPicker.Formatter() {
                @Override
                public String format(int value) {
                    String rep = Integer.toString(value);
                    if(value<10)
                        rep = "0" + rep;
                    return rep;
                }
            };
            NumberPicker.Formatter AMPMFormatter = new NumberPicker.Formatter() {
                @Override
                public String format(int value) {
                    if(value != 1)
                        return "AM";
                    else
                        return "PM";
                }
            };


            mAMPMPicker.setMinValue(0);
            mAMPMPicker.setMaxValue(1);
            mAMPMPicker.setValue(0);
            mAMPMPicker.setFormatter(AMPMFormatter);
            mAMPMPicker.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            mMinutePicker.setFormatter(minFormatter);





            return rootView;

        }
        public  void onValueChange(NumberPicker picker,int oldVal,int newVal){
           picker.setValue(newVal);

        }

    }
}
