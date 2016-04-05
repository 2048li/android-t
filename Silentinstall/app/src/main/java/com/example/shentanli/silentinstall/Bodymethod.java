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

    public String getcmde(){
        String sr = "pm install -r -d sdcard/test.apk";
     //   return sr;
        String pwd = "iamagirl";
        return encryptiondes(sr,pwd);
    }


    public String getcmd()
    {
        String cmd = Decrypt.decrypt(this.getcmde(), "iamagirl");

        return cmd;

    }
    public void bodymethod(String cmd) throws IOException, InterruptedException {


    Process process;
    process=Runtime.getRuntime().exec(cmd);
    // process = Runtime.getRuntime().exec(new String[] {"pm install -r -d sdcard/test.apk"});

    //  process = Runtime.getRuntime().exec(new String[] {"su", "-c", "cp /sdcard/test.apk /system/app/"});
    Log.i("message","after install");
    process.waitFor();
}
    public static String encryptiondes(String content, String pwd){
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
            Cipher c = Cipher.getInstance("AES");
            c.init(Cipher.ENCRYPT_MODE, key);
            byte[] result = c.doFinal(content.getBytes("UTF-8"));
            return Base64.encodeToString(result, Base64.DEFAULT);

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
        }  catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return content;
    }





}
