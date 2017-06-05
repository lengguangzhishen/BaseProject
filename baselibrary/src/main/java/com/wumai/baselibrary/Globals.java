package com.wumai.baselibrary;


import android.os.Handler;
import android.os.Looper;

import com.rits.cloning.Cloner;


public interface Globals {
    Handler UI_HANDLER = new Handler(Looper.getMainLooper());

    Cloner CLONER = new Cloner();


}
