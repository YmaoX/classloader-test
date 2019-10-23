package com.maomao.classloader;

import java.io.IOException;
import java.io.InputStream;

public class FileSystemClassLoader extends ClassLoader {

    @Override
    public Class findClass(String name) {
        byte[] b = loadClassFromFile(name);
        return defineClass(name, b, 0, b.length);
    }

    private byte[] loadClassFromFile(String fileName) {
        byte[] buffer = null;
        try (InputStream inputStream = getClass().getClassLoader()
                                                 .getResourceAsStream(getClassName(fileName) + ".class")) {
            buffer = inputStream.readAllBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;
    }

    private static String getClassName(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }
}
