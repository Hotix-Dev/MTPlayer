package com.tm.mtplayer.helpers;


import android.content.Context;
import android.content.SharedPreferences;

public class MySettings {

    //Shared Preferences Keys
    // Booleans
    public static final String KEY_FIRST_START = "firstStart";

    //Integer


    // Sharedpref file name
    private static final String PREF_NAME = "MtPlayerSettings";
    // Shared Preferences
    SharedPreferences pref;
    // Editor for Shared preferences
    SharedPreferences.Editor editor;
    // Context
    Context _context;
    // Shared pref mode
    int PRIVATE_MODE = 0;


    // Constructor
    public MySettings(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    /**********************************(  Getters & Setters )**************************************/
    // Booleans
    public boolean getFirstStart() {
        return pref.getBoolean(KEY_FIRST_START, true);
    }

    public void setFirstStart(boolean firstStart) {
        editor.putBoolean(KEY_FIRST_START, firstStart);
        editor.commit();
    }

    /*****************************************(  _______  )****************************************/
    //Clear MySettings details
    public void clearSettingsDetails() {
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();
    }

}
