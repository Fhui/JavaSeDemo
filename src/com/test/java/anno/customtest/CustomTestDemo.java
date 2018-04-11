package com.test.java.anno.customtest;

import org.junit.Test;

public class CustomTestDemo {


    @CustomTest
    public void fun1(){
        System.out.println("fun1");
    }

    @CustomTest
    public void fun2(){
        System.out.println("fun2");
    }

    @Test
    public void fun3(){
        System.out.println("fun3");
    }

}
