package com.wumai.baseproject.config;

import android.content.Intent;
import android.os.Bundle;

public class RecordResult {

    private final Bundle _Bundle;
    public static final String RESULT_KEY = "qupai.edit.result";
    public static final String XTRA_PATH = "path";
    public static final String XTRA_THUMBNAIL = "thumbnail";


    public RecordResult(Intent intent) {
        _Bundle = intent.getBundleExtra(RESULT_KEY);
    }

    public String getPath() {
        if (_Bundle != null) {
            return _Bundle.getString(XTRA_PATH);
        }
        return null;
    }

    public String[] getThumbnail() {
        if (_Bundle != null) {
            return _Bundle.getStringArray(XTRA_THUMBNAIL);
        }
        return null;
    }


    public static final String XTRA_DURATION = "duration";

    public long getDuration() {
        if (_Bundle != null) {
            return _Bundle.getLong(XTRA_DURATION);
        }
        return 0;
    }
}
