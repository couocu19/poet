package com.poets.util;

import com.spreada.utils.chinese.ZHConverter;

/**
 * 实现由中文繁体转为中文简体
 */
public class Big5ToChinese {
    public static String transfer(String s){
        ZHConverter converter = ZHConverter.getInstance(ZHConverter.SIMPLIFIED);
        String simplifiedStr = converter.convert(s);
        return simplifiedStr;

    }
}
