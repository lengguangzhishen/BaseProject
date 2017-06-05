package com.wumai.baseproject.customview.recycleviewrefresh.pullrefresh.internal;


import com.wumai.baselibrary.log.Logger;

public class Utils {

	static final String LOG_TAG = "PullToRefresh %s";

	public static void warnDeprecation(String depreacted, String replacement) {
		Logger.e(LOG_TAG + "You're using the deprecated " + depreacted + " attr, please switch over to " + replacement);
	}

}
