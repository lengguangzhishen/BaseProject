package com.wumai.baseproject.utils;

public class TextUtil {

    public static String subDataString(String data) {
        String s = data.substring(0, 7);
        return s;
    }


    public static boolean hasChinese(String str) {
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            String cStr = String.valueOf(chars[i]);
            if (cStr.matches("[\u4e00-\u9fa5]+")) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkNullString(String s) {
        if (android.text.TextUtils.isEmpty(s) || s.equals("null"))
            return true;
        return false;
    }

    /**
     * 验证手机格式
     */
    public static boolean isMobileNO(String mobiles) {
        String regex = "[1]\\d{10}";// "[1]"代表第1位为数字1，"\\d{10}"代表后面是可以是0～9的数字，有10位。
        return mobiles.matches(regex);
    }

    /**
     * 验证QQ格式
     */
    public static boolean isQQNO(String qq) {
        String regex = "^\\d{5,}$";
        return qq.matches(regex);
    }

    /**
     * 验证邮箱
     */
    public static boolean isEmail(String email) {
        String regex = "[a-zA-Z0-9_\\-\\.]+@[a-zA-Z0-9]+(\\.(com|cn|org|edu|hk|net|tw|us|gov))";
        // String regex =
        // "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        return email.matches(regex);
    }

    /**
     * 验证数字格式
     */
    public static boolean isNumberNO(String number) {
        String regex = "^[0-9]+$";
        return number.matches(regex);
    }


    /**
     * 验证用户名是否合法 返回空则为合法，其他为不合法，内容为不合法的理由
     */
    public static String isUserName(String userName) {
        if (userName.isEmpty()) {
            return "请输入会员号";
        }
        if (userName.length() < 6 || userName.length() > 18) {
            return "会员号至少需要6位";
        }
        char regex2 = userName.charAt(0);
        if (((regex2 >= 'a' && regex2 <= 'z') || (regex2 >= 'A' && regex2 <= 'Z'))) {

        } else {
            return "会员号需以字母开头";
        }
        String regex = "^[a-zA-Z][a-zA-Z0-9_]+$";
        if (!userName.matches(regex)) {
            return "会员号是以字母、下划线或者数字组成";
        }

        return null;
    }

    /**
     * 验证密码是否合法 返回空则为合法，其他为不合法，内容为不合法的理由
     */
    public static String isPassword(String password) {
        if (password.isEmpty()) {
            return "请输入密码";
        }
        if (password.length() < 6 || password.length() > 16) {
            return "密码至少需要6位";
        }
        if (password.contains(" ")) {
            return "密码中不能包含空格和汉字";
        }
        if (hasChinese(password)) {
            return "密码中不能包含空格和汉字";
        }
        return null;
    }

    /**
     * 只包含汉字；
     * LYL
     *
     * @param c
     * @return
     */
    public static boolean isCh(CharSequence c) {
        for (int i = 0; i < c.length(); i++) {
            int tmp = (int) c.charAt(i);
            if (tmp > 0 && tmp < 127) {
                return false;
            }
        }
        return true;
    }

    /**
     * 字符串连接
     *
     * @param strs
     * @return
     */
    public static String linkString(String... strs) {
        StringBuffer stringBuffer = new StringBuffer();
        for (String str : strs) {
            stringBuffer.append(str);
        }
        return stringBuffer.toString();
    }

    // 检测以避免重复多次点击
    private static long lastClickTime;

    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (0 < timeD && timeD < 800) {
            return true;
        }
        lastClickTime = time;
        return false;
    }
}
