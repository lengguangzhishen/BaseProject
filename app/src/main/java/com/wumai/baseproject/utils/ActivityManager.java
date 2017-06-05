package com.wumai.baseproject.utils;

import android.app.Activity;

import java.util.ArrayList;
import java.util.HashMap;

public class ActivityManager {

    public static final String DEFAULT_TAG = "all";
    /**
     * maintabactivity存入到activitymanager中的tag
     */
    public static final String MAIN_TAB_ACTIVITY = "main_tab_activity";

    private static ActivityManager instance;
    private HashMap<String, ArrayList<Activity>> mActivityMap = new HashMap<String, ArrayList<Activity>>();

    private ActivityManager() {

    }

    public static ActivityManager getInstance() {
        if (instance == null) {
            instance = new ActivityManager();
        }
        return instance;
    }

    public ArrayList<Activity> getAllActivities() {
        return mActivityMap.get(DEFAULT_TAG);
    }

    public void removeActivity(Activity activity) {
        removeActivity(DEFAULT_TAG, activity);
    }

    public void removeActivity(String tag, Activity activity) {
        if (mActivityMap != null) {
            ArrayList<Activity> list = mActivityMap.get(tag);
            if (list != null) {
                list.remove(activity);
            }
        }
    }

    public void putActivity(Activity activity) {
        putActivity(DEFAULT_TAG, activity);
    }

    public void putActivity(String tag, Activity activity) {
        ArrayList<Activity> list = mActivityMap.get(tag);
        if (list == null) {
            list = new ArrayList<Activity>();
        }
        list.add(activity);
        mActivityMap.put(tag, list);
    }

    /**
     * 结束所有的activity
     */
    public void clearAllActivity() {
        finishActivityList(DEFAULT_TAG);
        finishActivityList(MAIN_TAB_ACTIVITY);
    }

    /**
     * 结束出maintabactivity之外的所有的activity
     */
    public void clearDefaultActivity() {
        finishActivityList(DEFAULT_TAG);

    }

    public void clearMainTabActivity() {
        finishActivityList(MAIN_TAB_ACTIVITY);
    }

    public void finishActivityList(String tag) {
        if (mActivityMap != null) {
            ArrayList<Activity> activities = mActivityMap.get(tag);
            if (activities == null) {
                return;
            }
            for (Activity activity : activities) {
                activity.finish();
            }
            mActivityMap.remove(tag);
        }
    }

    public void holdOnlyMainActivity() {
        if (mActivityMap != null) {
            ArrayList<Activity> activities = mActivityMap.get(DEFAULT_TAG);
            if (activities == null) {
                return;
            }
            ArrayList<Activity> list = new ArrayList<>();
            for (Activity activity : activities) {

//                if (activity instanceof MainTabActivity) {
//                    list.add(activity);
//                } else if (activity instanceof SplashActivity
//                        || activity instanceof ClassRoomActivity
//                        || activity instanceof QuestionActivity
//                        || activity instanceof OffLineActivity
//                        || activity instanceof MineActivity) {
//                    list.add(activity);
//                } else {
//                    activity.finish();
//                }

            }
            mActivityMap.remove(DEFAULT_TAG);
            mActivityMap.put(DEFAULT_TAG, list);
        }
    }


    /**
     * 获取启动的activity的size
     * nan
     * 推送 返回到主界面
     *
     * @return
     */
    public int getAllActivityListSize() {
        return getActivityListSizeByTag(DEFAULT_TAG) + getActivityListSizeByTag(MAIN_TAB_ACTIVITY);
    }

    public Activity getMainTabActivity() {
        if (mActivityMap != null) {
            ArrayList<Activity> activities = mActivityMap.get(MAIN_TAB_ACTIVITY);
            if (activities != null) {
                return activities.get(0);
            }
        }
        return null;
    }

    /**
     * 得到相同tag的activity的数量
     * @param tag
     * @return
     */
    public int getActivityListSizeByTag(String tag) {
        if (mActivityMap != null) {
            ArrayList<Activity> activities = mActivityMap.get(tag);
            if (activities == null) {
                activities = new ArrayList<Activity>();
            }
            return activities.size();
        }
        return 0;
    }

}
