package com.jevely.widgetdemo;

import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

public class JavaTest {

    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
        }
    };


}
