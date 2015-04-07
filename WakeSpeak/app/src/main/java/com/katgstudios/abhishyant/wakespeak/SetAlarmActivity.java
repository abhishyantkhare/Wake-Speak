package com.katgstudios.abhishyant.wakespeak;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.Calendar;


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
        private ToggleButton mSunToggle;
        private ToggleButton mMonToggle;
        private ToggleButton mTuesToggle;
        private ToggleButton mWedToggle;
        private ToggleButton mThursToggle;
        private ToggleButton mFriToggle;
        private ToggleButton mSatToggle;
        private ArrayList<ToggleButton> weekdayButtons;
        private CheckBox mRepeatCheckBox;
        private EditText mAlarmName;
        private Button mSetAlarmButton;
        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_set_alarm, container, false);
            initNumberPickers(rootView);
            initWeekdayToggles(rootView);
            initRepeatCheckBox(rootView);
            initAlarmName(rootView);
            initSetAlarmButton(rootView);






            return rootView;

        }
        private void initNumberPickers(View rootView){
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());
            mHourPicker = (NumberPicker) rootView.findViewById(R.id.hourPicker);
            mHourPicker.setMinValue(1);
            mHourPicker.setMaxValue(12);
            mHourPicker.setValue(calendar.get(Calendar.HOUR));
            mMinutePicker = (NumberPicker) rootView.findViewById(R.id.minutePicker);
            mMinutePicker.setMinValue(0);
            mMinutePicker.setMaxValue(59);
            mMinutePicker.setValue(calendar.get(Calendar.MINUTE));
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

            String[] AMPMVals = {"AM","PM"};
            mAMPMPicker.setMinValue(0);
            mAMPMPicker.setMaxValue(1);
            mAMPMPicker.setDisplayedValues(AMPMVals);
            mAMPMPicker.setValue(calendar.get(Calendar.AM_PM));
            //mAMPMPicker.setFormatter(AMPMFormatter);
            mAMPMPicker.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            mMinutePicker.setFormatter(minFormatter);

        }
        public  void onValueChange(NumberPicker picker,int oldVal,int newVal){
           picker.setValue(newVal);

        }
        private void initWeekdayToggles(View rootView){
            weekdayButtons = new ArrayList<ToggleButton>();
            mSunToggle = (ToggleButton)rootView.findViewById(R.id.sunToggle);
            weekdayButtons.add(mSunToggle);
            mMonToggle = (ToggleButton)rootView.findViewById(R.id.monToggle);
            weekdayButtons.add(mMonToggle);
            mTuesToggle = (ToggleButton)rootView.findViewById(R.id.tuesToggle);
            weekdayButtons.add(mTuesToggle);
            mWedToggle = (ToggleButton)rootView.findViewById(R.id.wedToggle);
            weekdayButtons.add(mWedToggle);
            mThursToggle = (ToggleButton)rootView.findViewById(R.id.thursToggle);
            weekdayButtons.add(mThursToggle);
            mFriToggle = (ToggleButton)rootView.findViewById(R.id.friToggle);
            weekdayButtons.add(mFriToggle);
            mSatToggle = (ToggleButton)rootView.findViewById(R.id.satToggle);
            weekdayButtons.add(mSatToggle);
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());
            weekdayButtons.get(calendar.get(Calendar.DAY_OF_WEEK)-1).performClick();


        }
        private void initRepeatCheckBox(View rootView){
            mRepeatCheckBox = (CheckBox) rootView.findViewById(R.id.repeatCheckBox);
        }
        private void initAlarmName(View rootView){
            mAlarmName = (EditText) rootView.findViewById(R.id.alarmNameText);
        }
        private void initSetAlarmButton(View rootView){
            mSetAlarmButton = (Button) rootView.findViewById(R.id.setAlarmButton);
            mSetAlarmButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("SEND ALARM BUTTON",mAlarmName.getText().toString());
                    Intent setAlarmIntent = new Intent(getActivity(),AlarmActivity.class);
                    boolean[] selectedDays = new boolean[7];
                    for(int i =0; i <7;i++){
                        selectedDays[i] = weekdayButtons.get(i).isChecked();
                    }
                    String alarmName = "Alarm";
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTimeInMillis(System.currentTimeMillis());
                    if(!mAlarmName.getText().toString().equals(""))
                        alarmName = mAlarmName.getText().toString();
                    int hour = calendar.get(Calendar.HOUR);
                    Log.d("SET ALARM",alarmName);
                    int isRepeating = 0;
                    if(mRepeatCheckBox.isChecked())
                        isRepeating = 1;
                    AlarmObject alarm = new AlarmObject(alarmName,selectedDays,mHourPicker.getValue(),mMinutePicker.getValue(),mAMPMPicker.getValue(),isRepeating);
                    setAlarmIntent.putExtra("Alarm",alarm);
                    startActivity(setAlarmIntent);
                }
            });
        }

    }
}
