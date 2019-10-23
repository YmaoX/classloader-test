package com.maomao.classloader;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;

public class EncryptedClassLoader extends ClassLoader {

    @Override
    public Class findClass(String name) {
        byte[] b = loadClassFromFile(name);
        return defineClass(name, b, 0, b.length);
    }

    private byte[] loadClassFromFile(String fileName) {
        byte[] buffer = null;
        try (InputStream inputStream = getClass().getClassLoader()
                                                 .getResourceAsStream("hw.class")) {
            buffer = CipherUtils.decrypt(inputStream);
        } catch (IOException | InvalidKeyException e) {
            e.printStackTrace();
        }
        return buffer;
    }
}
