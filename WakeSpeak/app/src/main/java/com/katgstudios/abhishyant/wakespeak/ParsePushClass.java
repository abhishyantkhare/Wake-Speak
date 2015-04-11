package com.katgstudios.abhishyant.wakespeak;

import android.app.Application;
import android.util.Log;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParsePush;
import com.parse.PushService;
import com.parse.SaveCallback;

/**
 * Created by abhishyant on 4/7/15.
 */
public class ParsePushClass extends Application {

    @Override
    public void onCreate(){
       // Parse.initialize(this, "KLPNm4OOkvYB3G8AMns1HwiPjNqPzJ0o0M0Nz2jO", "cZbvnOZuTwgONSzaL42LJhi39ENCAkEMp55DpSLA");
        //PushService.setDefaultPushCallback(this,AlarmActivity.class);

    }

}
