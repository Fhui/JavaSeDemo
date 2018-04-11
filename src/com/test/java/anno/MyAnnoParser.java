package com.test.java.anno;

import java.lang.reflect.Method;

public class MyAnnoParser {

    @SuppressWarnings("all")
    public static void main(String[] args) throws NoSuchMethodException {
        Class myAnnoClass = MyAnnoTest.class;
        Method method = myAnnoClass.getMethod("test", null);
        MyAnno annotation = method.getAnnotation(MyAnno.class);
        /**在设置自定义注解的时候要定义元注解, 不然编译器会报错.**/
        System.out.println(annotation.name());
    }

}
