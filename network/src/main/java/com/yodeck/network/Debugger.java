package com.yodeck.network;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Debugger {
    public static final boolean IS_DEVELOPMENT_MODE = true;
    public static final String TAG = "com.network.network";

    public static void i(String tag, String msg) {
        if (IS_DEVELOPMENT_MODE) {
            Log.i(TAG, tag + " : " + msg);
        }
    }

    public static void d(String tag, String msg) {
        if (IS_DEVELOPMENT_MODE) {
            Log.d(TAG, tag + " : " + msg);
        }
    }

    public static void e(String tag, String msg) {
        if (IS_DEVELOPMENT_MODE) {
            Log.e(TAG, tag + " : " + msg);
        }
    }

    public static void v(String tag, String msg) {
        if (IS_DEVELOPMENT_MODE) {
            Log.v(TAG, tag + " : " + msg);
        }
    }

    public static String getTodayDate(String outPutFormat) {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat(outPutFormat);
        return df.format(c.getTime());
    }

}
