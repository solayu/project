package com.sola.sell.util;

public class MathUtil {

    public static final Double MONEY=0.01;
    /*
    *比较微信金额和系统给出的金额是否相等
    * */
    public static Boolean equals(Double big1,Double big2){
        Double result=Math.abs(big1-big2);
        if(result<MONEY){
            return true;
        }else{
            return false;
        }
    }

}
