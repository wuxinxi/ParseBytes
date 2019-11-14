package com.szxb.parse;

/**
 * 作者：wuxinxi on 2019/11/14
 * 包名：com.szxb.parse
 * TODO:一句话描述
 */
public class Entity {
    @FiledParams(type = ParamType.HEX)
    public String status;

    @FiledParams(len = 2, type = ParamType.HEX)
    public String rescode;

    @FiledParams(len = 2, type = ParamType.INTEGER)
    public int stationNo;

    @FiledParams(len = 10, type = ParamType.HEX)
    public String cardNo;

    @FiledParams(isNextLen = true, type = ParamType.INTEGER)
    public int stationNameLen;

    @FiledParams(isValidLen = false, type = ParamType.ASCII)
    public String stationName;

    @Override
    public String toString() {
        return "Demo{" +
                "status='" + status + '\'' +
                ", rescode='" + rescode + '\'' +
                ", stationNo=" + stationNo +
                ", cardNo='" + cardNo + '\'' +
                ", stationNameLen=" + stationNameLen +
                ", stationName='" + stationName + '\'' +
                '}';
    }
}
