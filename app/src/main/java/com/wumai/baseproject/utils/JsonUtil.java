package com.wumai.baseproject.utils;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by litengfei on 15/12/8.
 */
public class JsonUtil {

    public static final int INVALID_NUMBER = Utils.INVALID_NUMBER;

    public static String getString(JSONObject obj, String tag) {
        if (obj == null || tag == null) {
            return "";
        }
        try {
            return obj.optString(tag);
        } catch (Exception e) {
            Utils.printExceptionStackTrace(e);
        }
        return "";
    }

    public static double getDouble(JSONObject obj, String tag) {
        if (obj == null || tag == null) {
            return INVALID_NUMBER;
        }
        try {
            return obj.optDouble(tag, 0.0);
        } catch (Exception e) {
            try {
                String s = obj.optString(tag);
                if (TextUtils.isEmpty(s) || "null".equalsIgnoreCase(s)) {
                    return INVALID_NUMBER;
                }
                s = s.replaceAll(",", "");
                return Double.parseDouble(s);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return INVALID_NUMBER;
    }

    public static int getInt(JSONObject obj, String tag) {
        if (obj == null || tag == null) {
            return INVALID_NUMBER;
        }
        try {
            return obj.optInt(tag);
        } catch (Exception e) {
            try {
                String s = obj.optString(tag);
                if (TextUtils.isEmpty(s)) {
                    return INVALID_NUMBER;
                }
                s = s.replaceAll(",", "");
                return Integer.parseInt(s);
            } catch (Exception e1) {
                Utils.printExceptionStackTrace(e1);
            }
        }
        return INVALID_NUMBER;
    }

    public static long getLong(JSONObject obj, String tag) {
        if (obj == null || tag == null) {
            return INVALID_NUMBER;
        }
        try {
            return obj.optLong(tag);
        } catch (Exception e) {
            try {
                String s = obj.optString(tag);
                if (TextUtils.isEmpty(s)) {
                    return INVALID_NUMBER;
                }
                s = s.replaceAll(",", "");
                return Long.parseLong(s);
            } catch (Exception e1) {
                Utils.printExceptionStackTrace(e1);
            }
        }
        return INVALID_NUMBER;
    }

    public static <T> T parseJson(String json, Class<T> t) throws Exception {
        if (TextUtils.isEmpty(json)) {
            return null;
        }
        T instance = null;
//        try {
            instance = new Gson().fromJson(json, t);
//        } catch (JsonSyntaxException e) {
//            Utils.printExceptionStackTrace(e);
//        }
        return instance;
    }

    public static <T> T json2bean(String json, Class<T> t) {
        Gson gson = new Gson();
        return (T) gson.fromJson(json, t);
    }

    /**
     * 这个方法在编译器会把泛型给擦除, 因此用下边的方法
     * @param json
     * @param clazz
     * @param <T>
     * @return
     */
    public static<T> ArrayList<T> json2list(String json, Class<T> clazz) {
        ArrayList<T> foos = new Gson().fromJson(json, new TypeToken<ArrayList<T>>() {
        }.getType());
        return foos;
    }

    public static <T> ArrayList<T> fromJsonArray(String json, Class<T> clazz) throws Exception {
        ArrayList<T> lst =  new ArrayList<T>();

        JsonArray array = new JsonParser().parse(json).getAsJsonArray();
        for(final JsonElement elem : array){
            lst.add(new Gson().fromJson(elem, clazz));
        }
        return lst;
    }
}
