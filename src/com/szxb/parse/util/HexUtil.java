package com.szxb.parse.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Arrays;

/**
 * 作者: TangRen on 2019/8/3
 * TODO:HEX 工具类
 */
public class HexUtil {


    /**
     * 合并byte数组
     *
     * @param first .
     * @param rest  .
     * @return .
     */
    public static byte[] mergeByte(byte[] first, byte[]... rest) {
        int totalLength = first.length;
        for (byte[] array : rest) {
            totalLength += array.length;
        }
        byte[] result = Arrays.copyOf(first, totalLength);
        int offset = first.length;
        for (byte[] array : rest) {
            System.arraycopy(array, 0, result, offset, array.length);
            offset += array.length;
        }
        return result;
    }


    /**
     * 10进制串转为BCD码<br/>
     *
     * @param data 10进制串
     * @return byte[] BCD码
     */
    public static byte[] str2Bcd(String data) {
        if (data.length() == 0) {
            return new byte[0];
        }

        String str = data;
        // 奇数个数字需左补零
        if (str.length() % 2 != 0) {
            str = "0" + str;
        }
        ByteArrayOutputStream baOs = new ByteArrayOutputStream();
        char[] cs = str.toCharArray();
        for (int i = 0; i < cs.length; i += 2) {
            int high = cs[i] - 48;
            int low = cs[i + 1] - 48;
            baOs.write(high << 4 | low);
        }
        return baOs.toByteArray();
    }

    /**
     * byte[]bcd 转str
     *
     * @param bytes .
     * @return .
     */
    public static String bcd2Str(byte[] bytes) {
        try {
            if (bytes.length == 0) {
                return "00";
            }
            StringBuilder sb = new StringBuilder();
            for (byte aByte : bytes) {
                int h = ((aByte & 0xff) >> 4) + 48;
                sb.append((char) h);
                int l = (aByte & 0x0f) + 48;
                sb.append((char) l);
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bytesToHexString(bytes);
    }


    /**
     * 十六进制串转化为byte数组
     *
     * @return the array of byte
     */
    public static byte[] hex2byte(String hex)
            throws IllegalArgumentException {
        if (hex.length() % 2 != 0) {
            hex = hex + "0";
        }
        char[] arr = hex.toCharArray();
        byte[] b = new byte[hex.length() / 2];
        for (int i = 0, j = 0, l = hex.length(); i < l; i++, j++) {
            String swap = "" + arr[i++] + arr[i];
            int byteint = Integer.parseInt(swap, 16) & 0xFF;
            b[j] = Integer.valueOf(byteint).byteValue();
        }
        return b;
    }


    /**
     * char to byte[]
     *
     * @param c .
     * @return .
     */
    public static byte[] char2Byte(char c) {
        byte[] b = new byte[2];
        b[0] = (byte) ((c & 0xFF00) >> 8);
        b[1] = (byte) (c & 0xFF);
        return b;
    }

    /**
     * WORD
     * 将short转为低字节在前，高字节在后的byte数组
     *
     * @param n short
     * @return byte[]
     */
    public static byte[] word2Byte(int n) {
        byte[] b = new byte[2];
        b[0] = (byte) (n & 0xff);
        b[1] = (byte) (n >> 8 & 0xff);
        return b;
    }

    /**
     * word
     * 将长度为2的byte数组转换为16位int
     *
     * @param res byte[]
     * @return int
     */
    public static int byte2Word(byte[] res) {
        // | 表示安位或
        return (res[0] & 0xff) | ((res[1] << 8) & 0xff00);
    }


    /**
     * DWORD
     * int 转byte
     *
     * @param n int
     * @return 低在前高在后
     */
    public static byte[] dWord2byte(long n) {
        byte[] b = new byte[4];
        b[0] = (byte) (n & 0xff);
        b[1] = (byte) (n >> 8 & 0xff);
        b[2] = (byte) (n >> 16 & 0xff);
        b[3] = (byte) (n >> 24 & 0xff);
        return b;
    }

    /**
     * Dword
     * 将低字节数组转换为int
     *
     * @param b byte[]
     * @return int
     */
    public static int byte2Dword(byte[] b) {
        int s = 0;
        s = (((int) b[0] & 0xff)) | (((int) b[1] & 0xff) << 8) | (((int) b[2] & 0xff) << 16) | (((int) b[3] & 0xff) << 24);
        return s;
    }

    /**
     * byte 转hex
     *
     * @param src .
     * @return .
     */
    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder();
        if (src == null || src.length <= 0) {
            return "00";
        }
        for (byte aSrc : src) {
            int v = aSrc & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }


    /**
     * 获取文件MD5
     *
     * @param path 路径
     * @return md5
     */
    public static String getMD5(String path) {
        BigInteger integer = null;
        try {
            byte[] buffer = new byte[1024];
            int len = 0;
            MessageDigest digest = MessageDigest.getInstance("MD5");
            File file = new File(path);
            FileInputStream stream = new FileInputStream(file);
            while ((len = stream.read(buffer)) != -1) {
                digest.update(buffer, 0, len);
            }
            stream.close();
            byte[] data = digest.digest();
            return bytesToHexString(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * int 转hex
     *
     * @param integer .
     * @param maxLen  最大长度
     * @return hex
     */
    public static String int2Hex(int integer, int maxLen) {
        try {
            StringBuilder hex = new StringBuilder(Integer.toHexString(integer));
            int len = hex.length();
            for (int i = 0; i < maxLen - len; i++) {
                hex.insert(0, "0");
            }
            return hex.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return String.format("%0" + maxLen + "d", 0);
        }
    }


    /**
     * 16进制hex转10进制int
     *
     * @param hex .
     * @return .
     */
    public static int hex2Int(String hex) {
        try {
            return Integer.valueOf(hex, 16);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static int byte2intWord(byte[] res) {
        return (res[0] & 0xff) | ((res[1] << 8) & 0xff00);
    }

    /**
     * WORD
     * 将short转为低字节在前，高字节在后的byte数组
     *
     * @param n short
     * @return byte[]
     */
    public static byte[] wrod2Byte(int n) {
        byte[] b = new byte[2];
        b[0] = (byte) (n & 0xff);
        b[1] = (byte) (n >> 8 & 0xff);
        return b;
    }

    /**
     * DWORD
     * int 转byte
     *
     * @param n int
     * @return 低在前高在后
     */
    public static byte[] int2byteDword(long n) {
        byte[] b = new byte[4];
        b[0] = (byte) (n & 0xff);
        b[1] = (byte) (n >> 8 & 0xff);
        b[2] = (byte) (n >> 16 & 0xff);
        b[3] = (byte) (n >> 24 & 0xff);
        return b;
    }

    public static String toString(byte[] temp) {
        return new String(temp);
    }

    /**
     * Hex 高字节在前
     *
     * @param value .
     * @param len   .
     * @return .
     */
    public static byte[] int2ByteHL(int value, int len) {
        byte[] b = new byte[len];
        for (int i = 0; i < len; i++) {
            b[len - i - 1] = (byte) ((value >> 8 * i) & 0xff);
        }
        return b;
    }


    /**
     * 校验数据长度做处理
     *
     * @param data   .
     * @param length .
     * @return .
     */
    public static byte[] verifyData(byte[] data, int length) {
        if (data.length == length) {
            return data;
        }
        byte[] endData = new byte[length];
        if (data.length > length) {
            System.arraycopy(data, 0, endData, 0, endData.length);
        } else {
            System.arraycopy(data, 0, endData, 0, data.length);
        }
        return endData;
    }


    public static long hex2Long(String hexRes) {
        try {
            return Long.valueOf(hexRes, 16);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }


    /**
     * double 转byte[]
     *
     * @param d .
     * @return .
     */
    public static byte[] double2Bytes(double d) {
        long value = Double.doubleToRawLongBits(d);
        byte[] byteRet = new byte[8];
        for (int i = 0; i < 8; i++) {
            byteRet[i] = (byte) ((value >> 8 * i) & 0xff);
        }
        return byteRet;
    }

    /**
     * byte[] 转double
     *
     * @param arr .
     * @return .
     */
    public static double bytes2Double(byte[] arr) {
        long value = 0;
        for (int i = 0; i < 8; i++) {
            value |= ((long) (arr[i] & 0xff)) << (8 * i);
        }
        return Double.longBitsToDouble(value);
    }


}
