package com.example.shentanli.silentinstall;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class Encode_crypto
        {
        public static int a(String String1, String String2) throws NoSuchPaddingException, NoSuchAlgorithmException {
            SecretKeySpec localSecretKeySpec = new SecretKeySpec(String2.getBytes(), "DES");
            Cipher localCipher = Cipher.getInstance("DEX/ECB/PKCSSPadding");
            try {
                localCipher.init(1, localSecretKeySpec);
            } catch (InvalidKeyException e) {
                e.printStackTrace();
            }

            return 0;
        }

        }