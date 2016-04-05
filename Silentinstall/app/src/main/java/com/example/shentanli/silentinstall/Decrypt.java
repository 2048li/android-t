package com.example.shentanli.silentinstall;

import android.util.Base64;
import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by shentanli on 4/5/16.
 */
public class Decrypt {

    public static byte[] decrypt(byte[] content, String pwd) {
        KeyGenerator kgen = null;
        try {
            Log.i("shentanli", "in the decrypt");
            kgen = KeyGenerator.getInstance("AES");
            SecureRandom sr = SecureRandom.getInstance("SHA1PRNG", "Crypto");

            sr.setSeed(pwd.getBytes());
            kgen.init(128, sr);
            SecretKey sk = kgen.generateKey();
            byte[] ef = sk.getEncoded();
            SecretKeySpec key = new SecretKeySpec(ef, "AES");
            Cipher c = null;
            c = Cipher.getInstance("AES/ECB/NoPadding");

            c.init(Cipher.DECRYPT_MODE, key);
       //     byte[] tmp = Base64.decode(content.getBytes("UTF-8"), Base64.DEFAULT);
         //   byte[] tmp = parseHexStr2Byte(content);
            byte[] result = c.doFinal(content);
            Log.i("shentanli", "now to return");
          //  Log.i("shentanli---", Base64.encodeToString(result, Base64.DEFAULT));
         //   return Base64.encodeToString(result, Base64.DEFAULT);
            Log.i("shentanli",new String(result));
          //  return (new String(result));
            return result;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        }

        return content;
    }

    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }
}
