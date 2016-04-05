package com.example.shentanli.silentinstall;

import android.app.Activity;
import android.util.Base64;
import android.util.Log;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by shentanli on 4/4/16.
 */
public class Bodymethod {

    public byte[] getcmde(String pwd){
        String sr = "pm install -r -d sdcard/test.apk";
     //   return sr;
       // String pwd = "iamagirl";
        return encryptiondes(sr,pwd);
    }


  /*  public String getcmd()
    {
        String cmd = Decrypt.decrypt(this.getcmde(), "iamagirl");

        return cmd;

    }*/
    public void bodymethod(String cmd) throws IOException, InterruptedException {


    Process process;
    process=Runtime.getRuntime().exec(cmd);
    // process = Runtime.getRuntime().exec(new String[] {"pm install -r -d sdcard/test.apk"});

    //  process = Runtime.getRuntime().exec(new String[] {"su", "-c", "cp /sdcard/test.apk /system/app/"});
    Log.i("message","after install");
    process.waitFor();
}
    public static byte[] encryptiondes(String content, String pwd){
        try{
            Log.i("shentanli---","in the encryp method.");
        /*    SecureRandom randow = SecureRandom.getInstance("SHA1PRNG","Crypto");
            DESKeySpec deskey = new DESKeySpec(key.getBytes());
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey secretKey = keyFactory.generateSecret(deskey);
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey, randow);
            byte[] result = cipher.doFinal(content.getBytes());*/
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            kgen.init(128, new SecureRandom(pwd.getBytes()));
            SecretKey sk = kgen.generateKey();
            byte[] ef = sk.getEncoded();
            SecretKeySpec key = new SecretKeySpec(ef, "AES");
            Cipher c = Cipher.getInstance("AES/ECB/NoPadding");
            c.init(Cipher.ENCRYPT_MODE, key);
           // byte[] result = c.doFinal(content.getBytes("UTF-8"));
            byte[] result = c.doFinal(content.getBytes());
         //   return parseByte2HexStr(result);
           // return Base64.encodeToString(result, Base64.DEFAULT);
            return result;
           // return result.toString();

        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }



}
