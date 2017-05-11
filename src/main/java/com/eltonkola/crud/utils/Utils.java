package com.eltonkola.crud.utils;

/**
 * Created by elton on 4/21/17.
 */
public class Utils {

    public static String getFirstNWords(String s, int n) {
        if (s == null) return null;
        String [] sArr = s.split("\\s+");
        if (n >= sArr.length)
            return s;

        String firstN = "";

        for (int i=0; i<n-1; i++) {
            firstN += sArr[i] + " ";
        }
        firstN += sArr[n-1];
        return firstN;
    }

    public static int getWordsCount(String s) {
        if (s == null) return 0;
        String [] sArr = s.split("\\s+");
        return sArr.length;
    }



}
