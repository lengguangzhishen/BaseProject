package com.wumai.baselibrary.exception;

public final class Exceptions {

    private static boolean DEBUG;

    private Exceptions() {
    }

    public static void init(boolean debug) {
        DEBUG = debug;
    }

    public static void printStackTrace(Throwable e) {
        if (DEBUG) {
            e.printStackTrace();
        }
    }
}
