package com.example.android.publishapp.presentation;

import android.util.Log;

import static com.example.android.publishapp.presentation.Constant.DATABASE;

public class Logger {
    public static void logErrorDatabase(String errorText){
        Log.e(DATABASE, errorText);
    }
}
