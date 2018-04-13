package com.test.java.proxy.run;

import com.test.java.proxy.ITarget;
import com.test.java.proxy.impl.TargetImpl;

import java.lang.reflect.Proxy;

/**
 * Created by harry.feng on 2018/4/13 .
 */
public class TargetRun {

    public static void main(String[] args) {
        ITarget target = (ITarget) Proxy.newProxyInstance(
                TargetImpl.class.getClassLoader()
                , TargetImpl.class.getInterfaces()
                , (proxy, method, args1) -> method.invoke(new TargetImpl(), args1));

        target.fun1();
        target.fun2();
        target.fun3(10);

    }

}
