package com.util;

public class StringUtils {

    /**
     * 
     * Description:[字符数组是否包含该字符串]<br>
     * @author liufq
     * @update 2018年5月29日
     * @param s
     * @param str
     * @return
     */
    public static boolean containStr(String[] s, String str) {
        if (s == null || str == null) {
            return false;
        }
        for (int i = 0; i < s.length; i++) {
            if (s[i].equals(str)) {
                return true;
            }
        }
        return false;
    }
    public static boolean strEmpty(String str){
        if(str.isEmpty()||str.equals("")||str==null||str.length()<1){
            return true;
        }
        return false;
    }
    /**
     * 
     * Description:[字符串转整型]<br>
     * @author liufq
     * @update 2018��5��29��
     * @param str
     * @param def 非法返回值ֵ
     * @return
     */
    public static int parseInt(String str, int def) {
        int i = def;
        try {
            i = Integer.parseInt(str);
        } catch (Exception e) {

        }
        return i;
    }

    /**
     * 
     * Description:[字符串转整型，非法返回0]<br>
     * @author liufq
     * @update 2018年5月29日
     * @param str
     * @return
     */
    public static int parseInt(String str) {
        return parseInt(str, 0);
    }

    /**
     * 
     * Description:[字符串转长整型ֵ]<br>
     * @author liufq
     * @update 2018月5月29日
     * @param str
     * @param def 字符串非法时返回值
     * @return
     */
    public static long parseLong(String str, long def) {
        long lo = def;
        try {
            lo = Long.parseLong(str);
        } catch (Exception e) {

        }
        return lo;
    }

    public static long parseLong(String str) {
        return parseLong(str, 0);
    }
    
    public static String isNotEmpty(String str){
        String ss=str;
        if(str==null || str.equals("")){
            return str;
        }
        if(str.trim().contains("  ")){
            ss=str.replace("  ", " ");
            ss=isNotEmpty(ss);
        }
        return ss;
    }
    public static void main(String[] args) {
        String ss="my name                 is            Jay Zhou!";
        String str=StringUtils.isNotEmpty(ss);
        System.out.println(str);
    }
}