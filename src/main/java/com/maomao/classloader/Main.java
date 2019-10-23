package com.maomao.classloader;

import java.lang.reflect.InvocationTargetException;

public class Main {

    public static void main(String[] args) {
        FileSystemClassLoader ucl = new FileSystemClassLoader();
        EncryptedClassLoader ecl = new EncryptedClassLoader();
        try {
            Class<?> obj = ucl.loadClass("com.maomao.classloader.HelloWorld");
            obj.getMethod("sayHi").invoke(obj.newInstance());

            obj = ecl.loadClass("com.maomao.classloader.HelloWorld");
            obj.getMethod("sayHi").invoke(obj.newInstance());
        } catch (ClassNotFoundException | IllegalAccessException | InvocationTargetException | InstantiationException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
