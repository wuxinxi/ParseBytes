package com.szxb.parse;

import com.szxb.parse.util.HexParse;

import java.lang.reflect.Field;

/**
 * 作者：wuxinxi on 2019/11/14
 * 包名：com.szxb.parse
 * TODO:一句话描述
 */
public class HexParseHandler<T> {

    public synchronized T findParamsFiled(T objClass, byte[] data) {
        try {
            Field[] declaredFields = objClass.getClass().getDeclaredFields();
            int index = 0;
            int nextLen = -1;
            for (Field declaredField : declaredFields) {
                FiledParams filedParams = declaredField.getAnnotation(FiledParams.class);
                if (filedParams != null) {
                    System.out.println("类型：" + filedParams.type() + ",长度：" + filedParams.len() + ",长度是否有效：" + filedParams.isValidLen() + ",是否是下一个数据的长度：" + filedParams.isNextLen());
                    declaredField.setAccessible(true);
                    byte[] tempData = findData(data, index, nextLen > 0 ? nextLen : filedParams.len());
                    index += tempData.length;
                    Object value = HexParse.byte2Param(tempData, filedParams.type());
                    declaredField.set(objClass, value);
                    nextLen = filedParams.isNextLen() ? (int) value : -1;
                    System.out.println(filedParams.isNextLen() ? "下一个数据的长度：" + value + "\n" : "");
                }
            }
            return objClass;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }


    private static byte[] findData(byte[] data, int index, int len) {
        byte[] temp = new byte[len];
        System.arraycopy(data, index, temp, 0, len);
        return temp;
    }
}
