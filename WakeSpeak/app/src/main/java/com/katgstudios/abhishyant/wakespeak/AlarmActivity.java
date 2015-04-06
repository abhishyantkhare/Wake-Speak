package com.katgstudios.abhishyant.wakespeak;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.provider.AlarmClock;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Locale;

public class AlarmActivity extends ActionBarActivity implements OnInitListener{
    private int MY_DATA_CHECK_CODE = 0;
    private TextToSpeech mTTS;
    private PendingIntent alarmPendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_alarm);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new AlarmFragment())
                    .commit();
        }
        Intent checkTTSIntent = new Intent();
        checkTTSIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
        startActivityForResult(checkTTSIntent, MY_DATA_CHECK_CODE);
        Intent alarmIntent = new Intent(this,AlarmReceiver.class);
        alarmPendingIntent = PendingIntent.getBroadcast(this,1,alarmIntent,0);
        //int hour =29;
       // setAlarm(hour,10,true);
       // Log.d("TIME MINUTES",Integer.toString(hour));
        //comment for git testing
        registerReceiver(receiver, new IntentFilter("SPEAK"));
    }

    public void setAlarm(int minute, int hour, boolean isPM){
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        if(isPM && hour>=1) {


            hour += 12;
            Log.d("HOUR",Integer.toString(hour));
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND,0);


        manager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                alarmPendingIntent);

    }

    public static void addAlarm(String alarmName, String[] weekdays, int hour, int minute, boolean AMPM, boolean repeating){


    }


    //SPEAK
    public void speak(){
        mTTS.speak("Hello ahbeeshant!", TextToSpeech.QUEUE_FLUSH,null);

    }


    @Override
    public void onInit(int initStatus){
        if(initStatus == TextToSpeech.SUCCESS){
            mTTS.setLanguage(Locale.US);
            mTTS.setPitch(1.3f);
        }
        else if(initStatus == TextToSpeech.ERROR){
            Toast.makeText(this,"Please Try Again",Toast.LENGTH_LONG).show();
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_alarm, menu);
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
            speak();
            return true;
        }
        if(id == R.id.action_addalarm){
            Intent createAlarmIntent = new Intent(this,SetAlarmActivity.class);
            startActivity(createAlarmIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //OnActivityResult
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == MY_DATA_CHECK_CODE){
            if(resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
                mTTS = new TextToSpeech(this, this);
            }
            else{
                Intent installTTSIntent = new Intent();
                installTTSIntent.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
                startActivity(installTTSIntent);
            }
        }


    }
    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            speak();
        }
    };
    protected void onDestroy(){
        super.onDestroy();
        unregisterReceiver(receiver);
    }


    /**
     * A placeholder fragment containing a simple view.
     */

}
