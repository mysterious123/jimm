/*
 * Copyright (C) Unpublished JiuDaoTech Software, Inc. All rights reserved.
 * JiuDaoTech Software, Inc., Confidential and Proprietary.
 *
 * This software is subject to copyright protection
 *   under the laws of the Public of China and other countries.
 *
 * Unless otherwise explicitly stated, this software is provided
 *   by JiuDaoTech "AS IS".
 */

/************************************************************************
 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 ************************************************************************/
package common.util;

import common.exception.app.AppRuntimeException;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Sha512Hash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;

/**
 * <p>
 * 加密工具
 * 
 * @author CSJ
 */
public final class CryptoUtil {

    private static final Logger LOG = LoggerFactory.getLogger(CryptoUtil.class);

    private static final int BYTE_L = 0x0F;
    private static final int BYTE_H = 0xF0;
    private static final int BYTE_MAX = 0XFF;
    private static final int RADIX_16 = 0X10;
    private static final String HEX_SEQ = "0123456789ABCDEF";
    private static final String AES_ALGORITHM = "AES";
    private static final Properties ENV = new Properties();

    static {
        InputStream in = CryptoUtil.class.getClassLoader().getResourceAsStream("application.properties");
        try {
            ENV.load(in);
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }
    }

    private CryptoUtil() {
    }

    private static final String getCombinedSalt(String salt) {
        return ENV.getProperty("shiro.applicationSalt") + ":" + salt;
    }

    private static final Integer getIterations() {
        return Integer.parseInt(ENV.getProperty("shiro.hashIterations"));
    }

    /**
     * 生成随机散列盐
     * 
     * @return
     */
    public static final String getRandomSalt() {
        return new SecureRandomNumberGenerator().nextBytes().toBase64();
    }

    /**
     * 散列加密指定密码
     * 
     * @param rawPassphrase
     * @param salt
     * @return
     */
    public static final String encodePassphrase(String rawPassphrase, String salt) {
        return new Sha512Hash(rawPassphrase, getCombinedSalt(salt), getIterations()).toBase64();
    }

    public static final String encodePassphrase(String rawPassphrase) {
        return encodePassphrase(rawPassphrase, getRandomSalt());
    }

    /**
     * MD5加密
     * 
     * @param src
     * @return
     */
    public static String md5(String src) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
            md.reset();
            md.update(src.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            throw new AppRuntimeException("MD5 digest error!", e);
        } catch (UnsupportedEncodingException e) {
            throw new AppRuntimeException("UTF-8 not support!", e);
        }

        byte[] byteArray = md.digest();
        StringBuffer md5StrBuff = new StringBuffer();

        for (int i = 0; i < byteArray.length; i++) {
            if (Integer.toHexString(BYTE_MAX & byteArray[i]).length() == 1) {
                md5StrBuff.append("0").append(Integer.toHexString(BYTE_MAX & byteArray[i]));
            } else {
                md5StrBuff.append(Integer.toHexString(BYTE_MAX & byteArray[i]));
            }
        }

        return md5StrBuff.toString();
    }

    /**
     * SHA1加密
     * 
     * @param src
     * @return
     */
    public static String sha1(String src) {
        MessageDigest md;

        try {
            md = MessageDigest.getInstance("SHA1");
        } catch (NoSuchAlgorithmException e) {
            throw new AppRuntimeException(e);
        }

        byte[] result = md.digest(src.getBytes());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < result.length; i++) {
            sb.append(Integer.toString((result[i] & BYTE_MAX) + 0x100, RADIX_16).substring(1));
        }

        return sb.toString();
    }

    /**
     * 字符串转换为16进制字符串
     * 
     * @param str
     * @return
     */
    public static String str2Hex(String str) {
        String res = "";
        if (str != null && !"".equals(str)) {
            char[] hexChars = HEX_SEQ.toCharArray();
            StringBuilder builder = new StringBuilder();
            byte[] bytes = str.getBytes();
            for (int i = 0; i < bytes.length; i++) {
                byte b = bytes[i];
                int b_h = (b & BYTE_H) >> 4, b_l = (b & BYTE_L);
                builder.append(hexChars[b_h]).append(hexChars[b_l]);
            }
            res = builder.toString();
        }
        return res;
    }

    /**
     * 将16进制字符串转换为字符串
     * 
     * @param hexStr
     * @return
     */
    public static String hex2Str(String hexStr) {
        String res = "";
        if (hexStr != null && !"".equals(hexStr)) {
            char[] hexChars = hexStr.toCharArray();
            byte[] orgin = new byte[hexChars.length / 2];
            for (int i = 0; i < orgin.length; i++) {
                int b = (HEX_SEQ.indexOf(hexChars[2 * i]) << 4 | HEX_SEQ.indexOf(hexChars[2 * i + 1]));
                orgin[i] = (byte) (b & BYTE_MAX);
            }
            res = new String(orgin);
        }
        return res;
    }

    /**
     * <p>
     * AES对称加密
     * 
     * @param plainText
     * @return
     */
    public static String encryptAES(String plainText) {
        String algorithm = AES_ALGORITHM;
        SecretKey key;
        try {
            key = new SecretKeySpec("RadomSupe5Str0ng".getBytes("utf-8"), algorithm);
            Cipher cipher = Cipher.getInstance(algorithm);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encode = cipher.doFinal(plainText.getBytes());
            return Base64.encodeToString(encode);
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException
                | IllegalBlockSizeException | BadPaddingException e) {
            LOG.error(e.getMessage(), e);
        }
        return "";
    }

    /**
     * <p>
     * AES对称解密
     * 
     * @param encryptText
     * @return
     */
    public static String decryptAES(String encryptText) {
        String algorithm = AES_ALGORITHM;
        SecretKey key;
        try {
            key = new SecretKeySpec("RadomSupe5Str0ng".getBytes("utf-8"), algorithm);
            Cipher cipher = Cipher.getInstance(algorithm);
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] decode = cipher.doFinal(Base64.decode(encryptText));
            return new String(decode);
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException
                | IllegalBlockSizeException | BadPaddingException e) {
            LOG.error(e.getMessage(), e);
        }
        return "";
    }
}
