package com.wumai.baseproject.config;
/**
 * Created by litengfei on 15/12/8.
 */


import com.wumai.baselibrary.jumper.annotation.ActivityInfo;
import com.wumai.baselibrary.jumper.annotation.Extra;
import com.wumai.baseproject.LoginActivity;
import com.wumai.baseproject.utils.MyIntentHandler;

public interface Jumper extends IntentNames {

    @ActivityInfo(clz = LoginActivity.class)
    MyIntentHandler gotoLogin(@Extra(RADIO_INDEX) int id, @Extra(ISFROMLOGOUT) boolean isfromlogout);
}




