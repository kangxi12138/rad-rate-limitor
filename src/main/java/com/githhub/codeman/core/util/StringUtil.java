//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.githhub.codeman.core.util;


public final class StringUtil {
    public static final String EMPTY = "";
    public static boolean isEmpty(String string) {
        return null == string || "".equals(string);
    }
    public static boolean isNotEmptyTrim(String string) {
        return !isEmptyTrim(string);
    }
    public static boolean isEmptyTrim(String string) {
        if (isEmpty(string)) {
            return true;
        } else {
            String trim = trim(string);
            return isEmpty(trim);
        }
    }

    public static boolean isNotEmpty(String string) {
        return !isEmpty(string);
    }
    public static String trim(String original) {
        return isEmpty(original) ? original : original.trim();
    }

}
