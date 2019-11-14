package com.szxb.parse;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 作者：wuxinxi on 2019/11/14
 * 包名：com.szxb.parse
 * TODO:属性参数
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface FiledParams {

    /**
     * 是否是下一个数据的长度
     *
     * @return .
     */
    boolean isNextLen() default false;

    /**
     * 是否是有效长度
     * 比如[len][data]则为false
     * 实际长度为[len]
     *
     * @return .
     */
    boolean isValidLen() default true;

    /**
     * @return 有效长度
     */
    int len() default 1;

    /**
     * @return 类型
     */
    ParamType type() default ParamType.HEX;

}
