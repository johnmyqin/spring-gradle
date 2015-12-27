/*
 * @(#)SmartArith.java 2004-11-11
 *
 * Copyright 2004 Simon, Inc. All rights reserved.
 */
package com.mo.util;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * <p>Title: 提供Java精确算法类 </p>
 * <p>Date: 2015-12-26 20:39 </p>
 * @author <a href="mailto: ">mo</a>
 * @version 1.0.1
 */
public final class SmartArith {

    // 默认除法运算精度
    private static final int DEF_DIV_SCALE = 10;

    // 这个类不能实例化
    private SmartArith() {
    }

    /**
     * 提供精确的加法运算。
     * 
     * @param v1
     *            被加数
     * @param v2
     *            加数
     * @return 两个参数的和
     */
    public static BigDecimal add(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return add(b1, b2);
    }

    public static BigDecimal add(BigDecimal v1, BigDecimal v2) {
        if (v1 == null) {
            v1 = new BigDecimal("0");
        }
        if (v2 == null) {
            v2 = new BigDecimal("0");

        }
        return v1.add(v2);
    }

    /**
     * 提供精确的减法运算。
     * 
     * @param v1
     *            被减数
     * @param v2
     *            减数
     * @return 两个参数的差
     */
    public static BigDecimal sub(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return sub(b1, b2);
    }

    /**
     * 提供精确的减法运算(如果减数或被减数为null，则做为0计算)
     * 
     * @param v1
     *            被减数
     * @param v2
     *            减数
     * @return 两个参数的差
     */
    public static BigDecimal sub(BigDecimal v1, BigDecimal v2) {
        if (v1 == null) {
            v1 = new BigDecimal("0");
        }
        if (v2 == null) {
            v2 = new BigDecimal("0");

        }
        return v1.subtract(v2);
    }

    /**
     * 提供精确的乘法运算。
     * 
     * @param v1
     *            被乘数
     * @param v2
     *            乘数
     * @return 两个参数的积
     */
    public static BigDecimal mul(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return mul(b1, b2);
    }

    /**
     * 提供精确的乘法运算。
     * 
     * @param v1
     *            被乘数
     * @param v2
     *            乘数
     * @return 两个参数的积(如果乘数或被乘数为null，返回0)
     */
    public static BigDecimal mul(BigDecimal v1, BigDecimal v2) {
        if (v1 == null) {
            v1 = new BigDecimal("0");
        }
        if (v2 == null) {
            v2 = new BigDecimal("0");

        }
        return v1.multiply(v2);
    }

    /**
     * 提供精确的乘法运算。
     * 
     * @param v1
     *            被乘数
     * @param v2
     *            乘数
     * @return 两个参数的积(如果乘数或被乘数为null，返回0)
     */
    public static BigDecimal mul(String v1, String v2) {
        if (v1 == null) {
            v1 = "0";
        }
        if (v2 == null) {
            v2 = "0";
        }
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.multiply(b2);
    }

    /**
     * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到 小数点以后10位，以后的数字四舍五入。
     * 
     * @param v1
     *            被除数
     * @param v2
     *            除数
     * @return 两个参数的商
     */
    public static BigDecimal div(double v1, double v2) {
        return div(v1, v2, DEF_DIV_SCALE);
    }

    /**
     * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到 小数点以后10位，以后的数字四舍五入。
     * 
     * @param v1
     *            被除数
     * @param v2
     *            除数
     * @return 两个参数的商
     */
    public static BigDecimal div(BigDecimal v1, BigDecimal v2) {
        return div(v1, v2, DEF_DIV_SCALE);
    }

    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。
     * 
     * @param v1
     *            被除数
     * @param v2
     *            除数
     * @param scale
     *            表示表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */
    public static BigDecimal div(double v1, double v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));

        if (b2.doubleValue() == 0.0) {
            b2 = new BigDecimal("1");
        }
        return div(b1, b2, scale);
    }

    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。
     * 
     * @param v1
     *            被除数
     * @param v2
     *            除数
     * @param scale
     *            表示表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */
    public static BigDecimal div(BigDecimal v1, BigDecimal v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        if (v1 == null) {
            v1 = new BigDecimal("0");
        }
        if (v2 == null) {
            v2 = new BigDecimal("1");
        }
        if (v2.doubleValue() == 0.0) {
            v2 = new BigDecimal("1");

        }
        return v1.divide(v2, scale, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 求v1的v2次方，结果保留scale位小数
     * 
     * @param v1     底数
     * @param v2     次方数
     * @param scale  保留小数位
     * @return  v1的v2次方
     */
    public static BigDecimal pow(BigDecimal v1, BigDecimal v2, int scale) {
        if (v1 == null) {
            v1 = new BigDecimal("0");
        }
        if (v2 == null) {
            v2 = new BigDecimal("0");
        }
        
        double result = Math.pow(v1.doubleValue(), v2.doubleValue());
        if(Double.isNaN(result)) {
            throw new IllegalArgumentException("The result is NaN");
        }
        
        return round(result, scale);
    }
    
    /**
     * 求v1的v2次方，结果保留scale位小数
     * 
     * @param v1     底数
     * @param v2     次方数
     * @param scale  保留小数位
     * @return  v1的v2次方
     */
    public static BigDecimal pow(double v1, double v2, int scale) {
        return pow(new BigDecimal(v1), new BigDecimal(v2), scale);
    }

    /**
     * 求v1的v2次方（默认保留10位小数）
     * 
     * @param v1     底数
     * @param v2     次方数
     * @return  v1的v2次方
     */
    public static BigDecimal pow(double v1, double v2) {
        return pow(v1, v2, DEF_DIV_SCALE); 
    }
    
    /**
     * 求v1的v2次方根，结果保留scale位小数
     * 
     * @param v1     底数
     * @param v2     次方根
     * @param scale  保留小数位
     * @return  v1的v2次方根
     */
    public static BigDecimal root(BigDecimal v1, BigDecimal v2, int scale) {
        int precision = 15;   // 增大可以提高运算精度
   
        if(v1.doubleValue() < 0) {
            throw new IllegalArgumentException("The v1 must be more than zero");
        }
        
        BigDecimal result = new BigDecimal(1);
        BigDecimal pow = result;
        BigDecimal r = pow.divide(result);
        BigDecimal error = sub(pow, v1);
        BigDecimal p = mul(r, v2);
        BigDecimal epsilon = new BigDecimal(10).pow(-precision, new MathContext(precision));
        while (error.abs().compareTo(epsilon) > 0) {
            result = sub(result, error.divide(p, new MathContext(precision)));
            pow = result;
            int k = 1;
            while (k < v2.doubleValue()) {
                pow = mul(pow, result);
                k++;
            }
            r = pow.divide(result);
            error = sub(pow, v1);
            p = mul(r, v2);
        }
        return round(result, scale);
    }

    /**
     * 求v1的v2次方根，结果保留scale位小数
     * 
     * @param v1     底数
     * @param v2     次方根
     * @param scale  保留小数位
     * @return  v1的v2次方根
     */
    public static BigDecimal root(double v1, double v2, int scale) {
        return root(new BigDecimal(v1), new BigDecimal(v2), scale);
    }
    
    /**
     * 求v1的v2次方根（默认保留10位小数）
     * 
     * @param v1     底数
     * @param v2     次方根
     * @return  v1的v2次方根
     */
    public static BigDecimal root(double v1, double v2) {
        return root(new BigDecimal(v1), new BigDecimal(v2), DEF_DIV_SCALE);
    }

    /**
     * 提供精确的小数位四舍五入处理。
     * 
     * @param v
     *            需要四舍五入的数字
     * @param scale
     *            小数点后保留几位
     * @return 四舍五入后的结果
     */
    public static BigDecimal round(double v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(Double.toString(v));
        BigDecimal one = new BigDecimal("1");
        return b.divide(one, scale, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 提供精确的小数位四舍五入处理。
     * 
     * @param v
     *            需要四舍五入的数字
     * @param scale
     *            小数点后保留几位
     * @return 四舍五入后的结果
     */
    public static BigDecimal round(BigDecimal v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        if (v == null) {
            v = new BigDecimal("0");
        }
        BigDecimal b = new BigDecimal(Double.toString(v.doubleValue()));
        BigDecimal one = new BigDecimal("1");

        return b.divide(one, scale, BigDecimal.ROUND_HALF_UP);
    }

}
