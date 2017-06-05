package com.wumai.baseproject.config;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.common.reflect.Reflection;
import com.google.gson.Gson;
import com.wumai.baseproject.app.BaseProjectApplication;
import com.wumai.baselibrary.Globals;
import com.wumai.baselibrary.jumper.JumperInvokeHandler;

/**
 * Created by litengfei on 15/12/8.
 */
public interface Const extends Globals, StorageSchema, SpName {

    Gson GSON = new Gson();

    Jumper JUMPER = Reflection.newProxy(Jumper.class, new JumperInvokeHandler(BaseProjectApplication.getInstance()));

    SharedPreferences SP = BaseProjectApplication.getInstance()
            .getApplicationContext()
            .getSharedPreferences("default", Context.MODE_PRIVATE);



}
