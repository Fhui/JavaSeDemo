package com.test.java.proxy.impl;

import com.test.java.proxy.ITarget;

/**
 * Created by harry.feng on 2018/4/13 .
 */
public class TargetImpl implements ITarget {
    @Override
    public void fun1() {
        System.out.println("fun1 running");
    }

    @Override
    public String fun2() {
        System.out.println("fun2 running");
        return "fun2";
    }

    @Override
    public void fun3(int i){
        System.out.println("fun3...");
    }
}
