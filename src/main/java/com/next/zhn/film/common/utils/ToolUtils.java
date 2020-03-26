package com.next.zhn.film.common.utils;

public final class ToolUtils {

    public ToolUtils() {
    }

    /**
     * 判断字符串是否为空
     * @param src
     * @return
     */
    public static boolean isEmpty(String src) {

        if (src != null && src.trim().length() > 0) {
            return false;
        }

        return true;

    }
    /**
     * 判断字符串是否不为空
     * @param src
     * @return
     */
    public static boolean isNotEmpty(String src) {

        if (src == null && src.trim().length() == 0) {
            return false;
        }

        return true;

    }
}
