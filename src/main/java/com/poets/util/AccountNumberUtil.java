package com.poets.util;

/**
 *说明:根据注册手机号后四位和四位随机数字生成用户账号
 */
public class AccountNumberUtil {
    public static String getAccount(String num,String ranNum){
        String account = num+ranNum;
        return account;
    }

    public static String getRan(){
        int min = 1;
        int max = 10000;
        int ran = (int)(Math.random()*(max-min)+min);
        String result = String.valueOf(ran);
        return result;
    }

    //获取一个随机id
    public static int getRanNum(){
        int min = 1;
        int max = 311044;
        int ran = (int)(Math.random()*(max-min)+min);
        //emm因为这个范围的sid是无效的所以需要加一个判断
        while(ran>=1097 && ran<=2490){
            ran = (int)(Math.random()*(max-min)+min);
        }
        return ran;

    }
}
