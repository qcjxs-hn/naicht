package com.naic.common;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.AlgorithmParameters;
import java.security.Security;
import java.util.Base64;

public class Wechattophone {
    public static String decryptPhoneNumber(String encryptedData, String sessionKey, String iv) throws Exception {
        // 添加 Bouncy Castle 作为安全提供程序
        Security.addProvider(new BouncyCastleProvider());

        // Base64 解码
        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedData);
        byte[] sessionKeyBytes = Base64.getDecoder().decode(sessionKey);
        byte[] ivBytes = Base64.getDecoder().decode(iv);

        // 创建 Cipher 对象
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding", "BC");
        SecretKeySpec keySpec = new SecretKeySpec(sessionKeyBytes, "AES");
        AlgorithmParameters params = AlgorithmParameters.getInstance("AES");
        params.init(new IvParameterSpec(ivBytes));
        cipher.init(Cipher.DECRYPT_MODE, keySpec, params);

        // 解密
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);

        // 返回解密后的手机号字符串
        return new String(decryptedBytes, "UTF-8");
    }
}
