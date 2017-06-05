package com.wumai.baselibrary.storage;


import android.content.Context;

import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import com.google.common.io.Files;
import com.wumai.baselibrary.BaseApplication;
import com.wumai.baselibrary.log.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

public class FileEngine implements IStorageEngine {

    private File mFolder;

    public FileEngine() {
        this("json", BaseApplication.getGlobalContext());
    }

    public FileEngine(Context context) {
        this("json", context);
    }

    public FileEngine(String dirName) {
        this(dirName, BaseApplication.getGlobalContext());
    }

    public FileEngine(String dirName, Context context) {
        if (Strings.isNullOrEmpty(dirName)) {
            throw new RuntimeException("invalid dir name");
        }
        File cacheDir = context.getCacheDir();
        mFolder = new File(cacheDir, dirName);
        if (!mFolder.exists()) {
            mFolder.mkdir();
        }
    }

    @Override
    public void set(final String key, final String value) {
        try {
            Files.write(value.getBytes(), new File(mFolder, key));
        } catch (IOException e) {
        }
    }

    @Override
    public String get(final String key) {
        try {
            return Joiner.on("")
                    .join(Files.asCharSource(new File(mFolder, key), Charset.defaultCharset())
                            .readLines());
        } catch (IOException e) {
            return "";
        }
    }

    @Override
    public void delete(final String key) {
        new File(mFolder, key).delete();
    }

    @Override
    public boolean contains(final String key) {
        return new File(mFolder, key).exists();
    }
}
