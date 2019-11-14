package com.szxb.parse;

import com.szxb.parse.util.HexUtil;

/**
 * 作者：wuxinxi on 2019/11/14
 * 包名：com.szxb.parse
 * TODO:一句话描述
 */
class Mian {
    public static void main(String[] args) throws IllegalAccessException {
        byte[] status = new byte[]{0x09};
        byte[] resCode = new byte[]{(byte) 0xFF, (byte) 0xFF};
        byte[] stationN0 = HexUtil.int2ByteHL(25, 2);
        byte[] cardNo = new byte[]{0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x10};
        byte[] stationName = "深大北门".getBytes();
        byte[] stationNameLen = HexUtil.int2ByteHL(stationName.length, 1);
        byte[] data = HexUtil.mergeByte(status, resCode, stationN0, cardNo, stationNameLen, stationName);

        Entity entity = new Entity();
        HexParseHandler<Entity> hexParseHandler = new HexParseHandler<Entity>();
        Entity paramsFiled = hexParseHandler.findParamsFiled(entity, data);
        System.out.println(paramsFiled.toString());

    }
}
