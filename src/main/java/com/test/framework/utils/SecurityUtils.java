package com.test.framework.utils;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SecurityUtils {

    static final String KEY_SHA = "SHA";

    /**
     * 密码加密 SHA加密嵌套MD5加密
     *
     * @param password
     * @return
     */
    public static String encrypt(String password) {
        String result = "";
        byte[] data = password.getBytes();
        try {
            // 先SHA加密获取实例
            MessageDigest sha = MessageDigest.getInstance(KEY_SHA);

            // sha 加密
            sha.update(data);

            // 获得加密以后的字符串
            result = new BigInteger(sha.digest()).toString(32);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return result;
    }
    /**利用MD5进行加密
     * @param str  待加密的字符串
     * @return  加密后的字符串
     * @throws NoSuchAlgorithmException  没有这种产生消息摘要的算法
     * @throws UnsupportedEncodingException
     */
    public static String md5Encode(String inStr) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }

        byte[] byteArray = null;
        try {
            byteArray = inStr.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }
    public static void main(String[] args) throws NoSuchAlgorithmException {
        String or = "123";
        String re = encrypt(or);
        System.out.println(or);
        System.out.println("----SHA加密----");
        System.out.println(re.toString());

    }

}
