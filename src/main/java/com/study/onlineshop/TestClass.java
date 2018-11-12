package com.study.onlineshop;

import javax.crypto.KeyGenerator;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class TestClass {

    public static void main(String[] args)
    {

         String HASH_ALGORITM = "SHA-256";
        String password = "pass1";
        String sole = "qwerqfn1234234poqiv14313n1f";
        String encryptedPassword = "";
        //5ca98b6d73f3a9ac70005d7f6d4f6069   "qwerqfn1234234poqiv14313n1f"
        //a6a069841e8a931cc9602247a99dc993   "qwersadadaet123tysad234234poqiv14313n1f"
        //7e137766a30dbd1b371f74139bee7a2a   "asd132rdxy65vb6jc58ux29zu09un8y3r"
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update((password + sole).getBytes());
            //Get the hash's bytes
            byte[] bytes = md.digest();
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            encryptedPassword = sb.toString();

            System.out.println(encryptedPassword);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }




    /*try {
        String b64encoded = Base64.encode(ip);
        *//*MessageDigest messageDigest = MessageDigest.getInstance(HASH_ALGORITM);

        messageDigest.update(salt.getBytes(StandardCharsets.UTF_8));

        byte[] bytes = messageDigest.digest(password.getBytes(StandardCharsets.UTF_8));

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            stringBuilder.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        return stringBuilder.toString();*//*

    } catch (NoSuchAlgorithmException e) {
        e.printStackTrace();
        throw new RuntimeException("Can't encrypt password", e);
    }*/
}
