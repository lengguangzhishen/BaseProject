package com.wumai.baseproject.config;


import com.wumai.baseproject.BuildConfig;

/**
 * Created by litengfei on 15/11/08.
 */
public class AppInfo implements SpName {

    public final static int HOUR = 3600000;

    public final static int MINUTE = 60000;

    public final static int SECOND = 1000;

    public static final int PAGE_SIZE = 10;

    public static String BASE_DATA_DIR = "";

    public final static double DEF_COORDINATE = 1;

    public static boolean sDebug = BuildConfig.DEBUG; //Debug开关

    public static boolean isLastStatusBackground;

    public static boolean getHasShowNotice(int id) {
        return Const.SP.getBoolean(String.valueOf(id), false);
    }

    public static void setHasShowNotice(int id, boolean hasSHow) {
        Const.SP.edit()
                .putBoolean(String.valueOf(id), hasSHow)
                .commit();
    }

    public static String getVersionNumber() {
        return Const.SP.getString(IntentNames.EDITION, "0");
    }
    public static void setVersionNumber(String edition) {
        Const.SP.edit()
                .putString(IntentNames.EDITION, edition)
                .commit();
    }
}
