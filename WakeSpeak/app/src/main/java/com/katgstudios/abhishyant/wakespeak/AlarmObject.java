package com.katgstudios.abhishyant.wakespeak;

import android.os.Parcel;
import android.os.Parcelable;

import com.parse.Parse;
import com.parse.ParseClassName;
import com.parse.ParseObject;

import java.util.ArrayList;

/**
 * Created by abhishyant on 4/6/15.
 */
@ParseClassName("AlarmObject")
public class AlarmObject extends ParseObject implements Parcelable{

    public AlarmObject(){}



    public String getAlarmName() {
        return alarmName;
    }

    public boolean[] getWeekDays() {
        return weekDays;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public int getAM_PM() {
        return AM_PM;
    }

    public int getRepeating() {
        return repeating;
    }

    private String alarmName;
    private boolean[] weekDays = new boolean[7];
    private int hour;
    private int minute;
    private int AM_PM;
    private int repeating;
    public AlarmObject(String _alarmName, boolean[] _weekDays, int _hour,int _minute, int _AM_PM, int _repeating){
        alarmName = _alarmName;
        weekDays = _weekDays;
        hour = _hour;
        minute = _minute;
        AM_PM = _AM_PM;
        repeating = _repeating;


    }

    //Code for Parcelable
    public int describeContents(){

        return 0;
    }
    public void writeToParcel(Parcel out, int flags){
        out.writeString(alarmName);
        out.writeBooleanArray(weekDays);
        out.writeInt(hour);
        out.writeInt(minute);
        out.writeInt(AM_PM);
        out.writeInt(repeating);

    }
    public static final Parcelable.Creator<AlarmObject> CREATOR = new Creator<AlarmObject>(){
        public AlarmObject createFromParcel(Parcel in){
            return new AlarmObject(in.readString(),in.createBooleanArray(),in.readInt(),in.readInt(),in.readInt(),in.readInt());
        }
        public AlarmObject[] newArray(int size){
            return new AlarmObject[size];
        }

    };


}
