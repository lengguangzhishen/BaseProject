package com.wumai.refreshlibrary.recycleviewrefresh.pullrefresh.internal;


import android.app.Application;
import android.content.Context;

import java.lang.reflect.Method;

public class Utils {

	static final String LOG_TAG = "PullToRefresh %s";

    private static float density = -1F;

    private static Application app;

	public static void warnDeprecation(String depreacted, String replacement) {
//		Logger.e(LOG_TAG + "You're using the deprecated " + depreacted + " attr, please switch over to " + replacement);
	}

    public static int dip2px(float dpValue) {
        return (int) (dpValue * getDensity() + 0.5F);
    }

    public static float getDensity() {
        if (density <= 0F) {
            density = getApp().getResources().getDisplayMetrics().density;
        }
        return density;
    }

    private static Application getApp() {

        if (app == null) {
            try {
                // 在IDE进行布局预览时使用
                Class<?> renderActionClass = Class.forName("com.android.layoutlib.bridge.impl.RenderAction");
                Method method = renderActionClass.getDeclaredMethod("getCurrentContext");
                Context context = (Context) method.invoke(null);
                app = new MockApplication(context);
            } catch (Throwable ignored) {
                throw new RuntimeException("please invoke x.Ext.init(app) on Application#onCreate()"
                        + " and register your Application in manifest.");
            }
        }
        return app;
    }

    private static class MockApplication extends Application {
        public MockApplication(Context baseContext) {
            this.attachBaseContext(baseContext);
        }
    }


}
