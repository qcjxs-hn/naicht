package com.naic.common;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.SecureRandom;
import java.security.Security;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;



//dec加解密
public class Decjm {
    static {
        Security.addProvider(new BouncyCastleProvider());
    }
    //enc加密
    public static String encrypt(String plainText, String key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding", "BC");
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), "AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);

        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }
   //dec解密
   public static String decrypt(String encryptedText, String key) throws Exception {
       Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding", "BC");
       SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), "AES");
       cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);

       byte[] encryptedBytes = Base64.getDecoder().decode(encryptedText);
       byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
       return new String(decryptedBytes);
   }

    public static void main(String[] args) {
        try {
            String key = "1871982008200171";
//            String plainText = "1706779769603111";
//            String encryptedText = encrypt(plainText, key);
//
//            System.out.println("Encrypted Text: " + encryptedText);
            String encryptedText="L41foOmQvGA0aHa2lsdLhA==";
            String decryptedText = decrypt(encryptedText, key);
            System.out.println("Decrypted Text: " + decryptedText);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

