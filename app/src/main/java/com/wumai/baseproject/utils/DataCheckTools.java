package com.wumai.baseproject.utils;

import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by xzw on 2017/2/13.
 */

public class DataCheckTools {


    /**
     * 验证手机格式
     */
    public static boolean isMobileNO(String mobiles) {
    /*
    移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
    联通：130、131、132、152、155、156、185、186
    电信：133、153、180、189、（1349卫通）
    总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
    */
        String telRegex = "[1][0123456789]\\d{9}";//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(mobiles)) {
            return false;
        } else {
            return mobiles.matches(telRegex);
        }
    }

    /**
     * 验证密码规则 ：
     * 不全是大写字母，也不全是小写字母，
     * 也不全是数字，也不全是特殊字符的字符串，
     * 并且这个字符串全是可见字符,并且字符串长度在6到16位。
     * 必须满足这个要求的就只能是含有小写字母、大写字母、数字、特殊符号的两种及以上的字符串；
     *
     * @param passWord
     * @return
     */
    public static boolean isPasswordNO(String passWord) {
        String passwordRegex = "^(?![A-Z]+$)(?![a-z]+$)(?!\\d+$)(?![\\W_]+$)\\S{6,16}$";
        if (TextUtils.isEmpty(passWord)) {
            return false;
        } else {
            return passWord.matches(passwordRegex);
        }
    }

    /**
     * 钱数的正则表达式
     *
     * @param money
     * @return
     */
    public static boolean MoneyDataCheck(String money) {
        Pattern p = Pattern.compile("^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})?$");
        Matcher m = p.matcher(money);
        if (m.matches())
            return true;
        else
            return false;
    }
}
