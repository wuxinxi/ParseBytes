package com.szxb.parse;

/**
 * 作者：wuxinxi on 2019/11/14
 * 包名：com.szxb.parse
 * TODO:一句话描述
 */
public enum ParamType {
    /**
     * hex
     */
    HEX,
    /**
     * byte数组转成10进制
     * 高位在前低位在后
     */
    INTEGER,
    /**
     * 数组转10进制
     * 高位在前低位在后
     * 注意：此时filed必须是long类型
     */
    LONG,
    /**
     * 2字节
     * 低位在前高位在后
     */
    WORD,
    /**
     * 4字节
     * 低位在前高位在后
     */
    DWORD,
    /**
     * BCD码
     */
    BCD,
    /**
     * ASCII码
     */
    ASCII,

}
