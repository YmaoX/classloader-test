/**
 * <pre>
 * BootStrap ClassLoader: is machine code, not java class. It loads classes from the location rt.jar, called Primodial ClassLoader.
 * Extension ClassLoader: It loads files from jre/lib/ext directory or any other directory pointed by the system property java.ext.dirs.
 * System ClassLoader: It loads the Application type classes found in the environment variable CLASSPATH.
 * The Bootstrap ClassLoader is always given the higher priority.
 *
 * Delegation: forward request of class loading to parent class loader and only loads the class, if parent is not able to find or load class
 * Visibility: Child ClassLoader can see class loaded by Parent ClassLoader but vice-versa is not true.
 * Uniqueness: allows to load a class exactly once, which is basically achieved by delegation and ensures that child ClassLoader doesn't reload the class already loaded by parent.
 *
 * loadClass -> findClass (if not loaded already or can be loaded by parent)
 *
 * 1. load class from file: when calling classloader.loadClass(), full name must be used (package + class, without .class extension).
 *
 * </pre>
 */
package com.maomao.classloader;