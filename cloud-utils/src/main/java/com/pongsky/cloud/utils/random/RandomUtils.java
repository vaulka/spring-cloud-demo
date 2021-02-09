package com.pongsky.cloud.utils.random;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Date;

/**
 * 随机数工具
 *
 * @author pengsenhao
 * @create 2019-10-21 9:55
 */
public class RandomUtils {

    /**
     * 六位随机数
     *
     * @return 六位随机数
     */
    public static Integer sixRandom() {
        return (int) ((Math.random() * 9 + 1) * 100000);
    }

    /**
     * 创建一个指定长度的随机字符串
     * 字符串由：【a-zA-Z0-9】组成
     *
     * @param length 长度
     * @return 随机字符串
     */
    public static String randomString(int length) {
        return RandomStringUtils.randomAlphanumeric(length);
    }

    /**
     * 创建一个指定长度的随机字符串
     * 字符串由：【0-9】组成
     *
     * @param length 长度
     * @return 随机字符串
     */
    public static String randomInteger(int length) {
        return RandomStringUtils.randomNumeric(length);
    }

    /**
     * 获取时间戳 + 随机数字
     *
     * @return 获取时间戳 + 随机数字
     */
    public static String timestampAndRandomInteger(int length) {
        return DateFormatUtils.format(new Date(), "yyyyMMddHHmmssS") + randomInteger(length);
    }

}
