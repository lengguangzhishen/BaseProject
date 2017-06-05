package com.wumai.baseproject.utils;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;

import com.wumai.baseproject.app.BaseProjectApplication;
import com.wumai.baselibrary.exception.Exceptions;
import com.wumai.baselibrary.log.Logger;
import com.wumai.baseproject.config.AppInfo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.util.zip.GZIPOutputStream;

/**
 * Created by litengfei on 15/12/8.
 */
public final class FileUtils {

    private FileUtils() {
    }

    /**
     * 在SD卡上创建文件
     */
    public static File createFile(String dirName, String fileName) throws IOException {
        File dir = new File(dirName);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File file = new File(dir, fileName);
        if (!file.exists()) {
            file.createNewFile();
        }
        return file;
    }

    /**
     * 将一个InputStream里面的数据写入到SD卡中
     */
    public static void write(String fileName, InputStream input) {
        write(AppInfo.BASE_DATA_DIR, fileName, input);
    }

    /**
     * 将一个InputStream里面的数据写入到SD卡中
     */
    public static void write(String dir, String fileName, InputStream input) {
        OutputStream output = null;
        try {
            File file = createFile(dir, fileName);
            output = new FileOutputStream(file);
            byte[] buffer = new byte[1024];
            int len;
            while ((len = input.read(buffer)) > 0) {
                output.write(buffer, 0, len);
            }
            output.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (output != null) {
                    output.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void deleteFile(String file) {
        if (TextUtils.isEmpty(file)) {
            return;
        }
        deleteFile(new File(file));
    }

    public static void deleteFile(File file) {
        if (file == null || !file.exists()) {
            return;
        }
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null && files.length > 0) {
                for (File f : files) {
                    deleteFile(f);
                }
            }
        }
        file.delete();
    }

    public static void printSharedPreference() {
        File sp = new File(BaseProjectApplication.getInstance().getFilesDir().getParentFile(), "shared_prefs");
        if (sp != null && sp.listFiles() != null) {
            for (File spFile : sp.listFiles()) {
                String log = readAll(spFile.getAbsolutePath());
//                L.v("FileUtils.printSharedPreference,fileName=%s,content=%s", spFile.getName(), log);
            }
        }
    }

    public static void writeLine(String fileName, String text) {
        if (TextUtils.isEmpty(fileName) || TextUtils.isEmpty(text)) {
            return;
        }
        FileWriter filerWriter = null;
        try {
            filerWriter = new FileWriter(fileName, true);
            filerWriter.write(text);
            filerWriter.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (filerWriter != null) {
                try {
                    filerWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 写入数据到文件
     * 如果文件存在，源数据将被替换
     */
    public static void writeReplace(String fileName, String text) {
        if (TextUtils.isEmpty(fileName) || TextUtils.isEmpty(text)) {
            return;
        }
        File file = new File(fileName);
        if (!file.exists()) {
            final File parentFile = file.getParentFile();
            if (!parentFile.exists() && !parentFile.mkdirs()) {
                return;
            }
            try {
                if (!file.createNewFile()) {
                    return;
                }
            } catch (IOException e) {
                return;
            }
        }
        FileWriter filerWriter = null;
        try {
            filerWriter = new FileWriter(fileName, false);
            filerWriter.write(text);
            filerWriter.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (filerWriter != null) {
                try {
                    filerWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void write(String fileName, String text) {
        if (TextUtils.isEmpty(fileName) || TextUtils.isEmpty(text)) {
            return;
        }
        File file = new File(fileName);
        if (!file.exists()) {
            final File parentFile = file.getParentFile();
            if (!parentFile.exists() && !parentFile.mkdirs()) {
                return;
            }
            try {
                if (!file.createNewFile()) {
                    return;
                }
            } catch (IOException e) {
                return;
            }
        }
        FileWriter filerWriter = null;
        try {
            filerWriter = new FileWriter(fileName, true);
            filerWriter.write(text);
            filerWriter.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (filerWriter != null) {
                try {
                    filerWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static boolean checkFile(File file, double day) {
        if (!file.exists()) {
            return false;
        }
        if (day <= 0) {
            day = 1;
        }
        long lastModified = file.lastModified();
        if (System.currentTimeMillis() - lastModified <= day * 24 * AppInfo.HOUR) {
            return true;
        }
        file.delete();
        return false;
    }

    /**
     * check if a given exists
     *
     * @param file
     * @return boolean
     */
    public static boolean exists(File file) {
        return file.exists();
    }

    // 搜索文件
    public static boolean search(File dir, String fileName) {
        if (dir == null || !dir.exists() || TextUtils.isEmpty(fileName)) {
            return false;
        }
        File file = new File(dir, fileName);
        return checkFile(file, 7);
    }


    public static void copyFile(File src, File dst) throws IOException {
        @SuppressWarnings("resource") FileChannel inChannel = new FileInputStream(src).getChannel();
        @SuppressWarnings("resource") FileChannel outChannel = new FileOutputStream(dst).getChannel();
        try {
            inChannel.transferTo(0, inChannel.size(), outChannel);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inChannel != null) {
                inChannel.close();
            }
            outChannel.close();
        }
    }

    public static void copyAndPressFile(File src, File dst) throws IOException {
        GZIPOutputStream out = null;
        FileInputStream in = null;
        try {
            out = new GZIPOutputStream(new FileOutputStream(dst));
            in = new FileInputStream(src);
            byte[] buf = new byte[502];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
//            L.v("FileUtils:copyAndPressFile: 压缩前：%s byte,压缩后：%s byte,压缩效果：%s byte", getFileSize(src),
//                    getFileSize(dst), (double) getFileSize(dst) / getFileSize(src));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

    }

    public static String readAll(String filename) {
        if (TextUtils.isEmpty(filename)) {
            return "";
        }
        File file = new File(filename);
        if (!file.exists()) {
            return "";
        }
        FileReader fr = null;
        BufferedReader reader = null;
        try {
            fr = new FileReader(file);
            StringBuilder buffer = new StringBuilder();
            reader = new BufferedReader(fr);
            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line).append("\n");
            }
            return buffer.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            Exceptions.printStackTrace(e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    Exceptions.printStackTrace(e);
                }
            }
            if (fr != null) {
                try {
                    fr.close();
                } catch (IOException e) {
                    Exceptions.printStackTrace(e);
                }
            }
        }
        return "";
    }

    public static String readAllwithoutCRLF(String filename) {

        if (TextUtils.isEmpty(filename)) {
            return "";
        }
        File file = new File(filename);
        if (!file.exists()) {
            return "";
        }
        FileReader fr = null;
        BufferedReader reader = null;
        try {
            fr = new FileReader(file);
            StringBuilder buffer = new StringBuilder();
            reader = new BufferedReader(fr);
            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            return buffer.toString();
        } catch (FileNotFoundException e) {
            Exceptions.printStackTrace(e);
        } catch (IOException e) {
            Exceptions.printStackTrace(e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    Exceptions.printStackTrace(e);
                }
            }
            if (fr != null) {
                try {
                    fr.close();
                } catch (IOException e) {
                    Exceptions.printStackTrace(e);
                }
            }
        }
        return "";
    }

    public static void chmod(String permission, String path) {
        try {
            Runtime.getRuntime().exec("chmod " + permission + " " + path);
        } catch (IOException e) {
            Exceptions.printStackTrace(e);
        }
    }

    /**
     * 是否存在SD卡
     *
     * @return
     */
    public static boolean hasSDCard() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }

    public static String getBaseDataDir(Context context) {
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            return Environment.getExternalStorageDirectory().getPath();
        } else {
            return context.getFilesDir().getPath();
        }
    }

    //得到文件的大小
    public static int getFileSize(File file) {
        int fileSize = 0;
        if (file.exists()) {

            FileInputStream fis = null;
            try {
                fis = new FileInputStream(file);
                fileSize = fis.available();
            } catch (FileNotFoundException e) {
                Exceptions.printStackTrace(e);
                Logger.e("FileUtils.getFileSize file not found");
            } catch (IOException e) {
                Exceptions.printStackTrace(e);
                Logger.e("FileUtils.getFileSize io exception");
            } catch (Exception e) {
                Exceptions.printStackTrace(e);
                Logger.e("FileUtils.getFileSize exception " + e.toString());
            } finally {
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        Exceptions.printStackTrace(e);
                    }
                }
            }
        } else {
            fileSize = 0;
        }
        return fileSize;
    }

    /**
     * 获得一个文件夹的总大小
     * @param f
     * @return
     */
    public static long getFolderSize(File f)
    {
        long size = 0;
        File flist[] = f.listFiles();
        for (int i = 0; i < flist.length; i++)
        {
            if (flist[i].isDirectory())
            {
                size = size + getFileSize(flist[i]);
            } else
            {
                if(!flist[i].getName().contains("tmp"))
                size = size + flist[i].length();
            }
        }
        return size;
    }
}
