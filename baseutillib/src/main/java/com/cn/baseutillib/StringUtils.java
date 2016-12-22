package com.cn.baseutillib;

import android.text.TextUtils;

import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2016/3/24 0024.
 */
public class StringUtils {

    /**
     *  String 转为int
     *  @param strNumber 数字的类型String
     * */
    public static final int parseInt(String strNumber){
        int number =0;
        if (!TextUtils.isEmpty(strNumber)){
            try{
                number = Integer.parseInt(strNumber);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return  number;
    }

    /**
     *  String 转为double
     *  @param strNumber 数字的类型String
     * */
    public static final double parseDouble(String strNumber){
        double d = 0.00;
        if (!TextUtils.isEmpty(strNumber)){
            try {
                d = Double.parseDouble(strNumber);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return d;
    }

    /**
     * 获取两位小数
     * @param doub
     * @return
     */
    public static final String getTwoDecimal(Double doub)
    {
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(doub);
    }

    /**
     * 获取一位小数
     * @param doub
     * @return
     */
    public static final String getOneDecimal(Double doub){
        DecimalFormat df = new DecimalFormat("0.0");
        return df.format(doub);
    }
    /**
     * 没有小数
     * @param doub
     * @return
     */
    public static final String getNonDecimal(Double doub){
        DecimalFormat df = new DecimalFormat("0");
        return df.format(doub);
    }

    public static String parserInt2String(int value) {

        try {
            return String.valueOf(value);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 判断是否ip地址
     * @param address
     * @return
     */
    public static boolean isIPAddress(String address){
        return isMatch("^[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}",address);
    }

    private static boolean isMatch(String regex, String orginal){
        if (orginal == null || orginal.trim().equals("")) {
            return false;
        }
        Pattern pattern = Pattern.compile(regex);
        Matcher isNum = pattern.matcher(orginal);
        return isNum.matches();
    }
}
