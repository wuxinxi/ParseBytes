package com.szxb.parse.util;


import com.szxb.parse.ParamType;

/**
 * 作者：wuxinxi on 2019/11/14
 * 包名：com.szxb.parse.util
 * TODO:一句话描述
 */
public class HexParse {

    public static Object byte2Param(byte[] data, ParamType type) {
        try {
            if (type == ParamType.HEX) {
                return HexUtil.bytesToHexString(data);
            }

            if (type == ParamType.INTEGER) {
                return HexUtil.hex2Int(HexUtil.bytesToHexString(data));
            }

            if (type == ParamType.LONG) {
                return HexUtil.hex2Long(HexUtil.bytesToHexString(data));
            }

            if (type == ParamType.WORD) {
                return HexUtil.byte2Word(data);
            }

            if (type == ParamType.DWORD) {
                return HexUtil.byte2Dword(data);
            }

            if (type == ParamType.BCD) {
                return HexUtil.bcd2Str(data);
            }

            if (type == ParamType.ASCII) {
                return new String(data);
            }
            return HexUtil.bytesToHexString(data);
        } catch (Exception e) {
            e.printStackTrace();
            return error(type);
        }
    }

    private static Object error(ParamType type) {
        if (type == ParamType.INTEGER || type == ParamType.LONG || type == ParamType.WORD || type == ParamType.DWORD) {
            return 0;
        }
        return "99";
    }
}
