package com.wumai.baselibrary.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;


import com.wumai.baselibrary.exception.Exceptions;
import com.wumai.baselibrary.jumper.annotation.ThreadSafe;

import java.util.HashMap;

@ThreadSafe(false)
public final class MetaDataResolver {

    private static HashMap<String, Object> sCache = new HashMap<String, Object>();

    private MetaDataResolver() {
    }

    public static String resolveString(Context context, String key, String defValue) {
        String cache = (String) sCache.get(key);
        if (cache != null) {
            return cache;
        }

        PackageManager packageManager = context.getPackageManager();
        ApplicationInfo applicationInfo;
        try {
            applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            if (applicationInfo != null && applicationInfo.metaData != null) {
                return (String) applicationInfo.metaData.get(key);
            }
        } catch (NameNotFoundException e) {
            Exceptions.printStackTrace(e);
        }
        return defValue;
    }
}
