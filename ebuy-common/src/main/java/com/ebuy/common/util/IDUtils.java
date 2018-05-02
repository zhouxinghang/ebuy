package com.ebuy.common.util;

import java.util.Random;

/**
 * Created by admin on 2017/12/24.
 * 各种id生成策略
 */
public class IDUtils {

    /**
     * 图片名生成
     * @return
     */
    public static String getImageName() {
        long millis = System.currentTimeMillis();
        Random random = new Random();
        //加上3位随机数
        int end3 = random.nextInt(999);
        //不足3位前补0
        String str = millis + String.format("%03d", end3);
        return str;
    }

    /**
     * 商品ID生成
     * @return
     */
    public static long getItemId() {
        long millis = System.currentTimeMillis();
        Random random = new Random();
        int end2 = random.nextInt(99);
        String str = millis + String.format("%02d", end2);
        long id = new Long(str);
        return id;
    }

    /**
     * 获取验证码
     * @return
     */
    public static long getCode() {
        long millis = System.currentTimeMillis();
        Random random = new Random();
        int end2 = random.nextInt(99);
        String str = millis % 10000 + String.format("%02d", end2);
        long code = new Long(str);
        return code;
    }

    public static String getRandomString(int length){
        //定义一个字符串（A-Z，a-z，0-9）即62位；
        String str="zxcvbnmlkjhgfdsaqwertyuiopQWERTYUIOPASDFGHJKLZXCVBNM1234567890";
        //由Random生成随机数
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        //长度为几就循环几次
        for(int i=0; i<length; ++i){
            //产生0-61的数字
            int number=random.nextInt(62);
            //将产生的数字通过length次承载到sb中
            sb.append(str.charAt(number));
        }
        //将承载的字符转换成字符串
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(getCode());
        System.out.println(String.format("%04d", System.currentTimeMillis()));
        System.out.println(getRandomString(8));
    }
}
