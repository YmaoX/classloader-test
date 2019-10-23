package com.maomao.classloader;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

/*
https://stackoverflow.com/questions/27059587/why-is-a-secretkeyspec-needed-when-deriving-a-key-from-a-password-in-java

 */
public class CipherUtils {
    private static SecretKeySpec keySpec;
    private static Cipher        cipher;

    static {
        try {
            String password = "p9xdAkN6vboWC1YB";
            String salt = "boelrQqS6ogYNNXN";
            PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt.getBytes(), 1000, 128);
            SecretKey key = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1").generateSecret(spec);
            keySpec = new SecretKeySpec(key.getEncoded(), "AES");
            cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
    }

    public static void encrypt(byte[] content, String fileName) throws InvalidKeyException, IOException {
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);
        try (FileOutputStream fileOut = new FileOutputStream(fileName);
             CipherOutputStream cipherOut = new CipherOutputStream(fileOut, cipher)) {
            cipherOut.write(content);
        }
    }

    public static byte[] decrypt(InputStream is) throws IOException, InvalidKeyException {
        cipher.init(Cipher.DECRYPT_MODE, keySpec);
        try (CipherInputStream cipherIn = new CipherInputStream(is, cipher)) {
            return cipherIn.readAllBytes();
        }
    }

    public static void main(String[] args) throws Exception {
        byte[] input = CipherUtils.class.getClassLoader().getResourceAsStream("HelloWorld.class").readAllBytes();
        String file = "/Users/yuemxu/Documents/workspace/classloader/tmp";
        CipherUtils.encrypt(input, file);
    }
}
