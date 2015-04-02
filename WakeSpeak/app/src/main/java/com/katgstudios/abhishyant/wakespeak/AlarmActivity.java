package com.katgstudios.abhishyant.wakespeak;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.widget.Toast;

import java.util.Locale;

public class AlarmActivity extends ActionBarActivity implements OnInitListener{
    private int MY_DATA_CHECK_CODE = 0;
    private TextToSpeech mTTS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_alarm);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
        Intent checkTTSIntent = new Intent();
        checkTTSIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
        startActivityForResult(checkTTSIntent, MY_DATA_CHECK_CODE);
    }

    //SPEAK
    public void speak(){
        mTTS.speak("Hello ahbeeshant!", TextToSpeech.QUEUE_FLUSH,null);

    }

    @Override
    public void onInit(int initStatus){
        if(initStatus == TextToSpeech.SUCCESS){
            mTTS.setLanguage(Locale.UK);
            mTTS.setPitch(1.0f);
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


    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_alarm, container, false);
            return rootView;
        }
    }
}
