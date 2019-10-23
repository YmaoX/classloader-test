package com.maomao.classloader;

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;

public class Main {

    public static void main(String[] args) throws MalformedURLException {
        FileSystemClassLoader ucl = new FileSystemClassLoader();
        try {
            Class<?> obj = ucl.loadClass("com.maomao.classloader.HelloWorld");
            obj.getMethod("sayHi").invoke(obj.newInstance());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
