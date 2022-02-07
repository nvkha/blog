package com.nvkha.utils;

public class StringUtils {
    public static boolean isNullOrEmpty(String str) {
        if(str == null) return true;
        if(str.length() == 0) return true;
        return false;
    }
}
