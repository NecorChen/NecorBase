package com.necor.necorbase;

public class StringUtils {

    /**
     * 是否是空格
     */
    public static boolean isSpace(String str) {
        if (str == null) return true;
        for (int i = 0, len = str.length(); i < len; ++i) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

}
