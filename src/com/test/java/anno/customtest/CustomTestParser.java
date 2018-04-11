package com.test.java.anno.customtest;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 自定义Test注解解析
 */
public class CustomTestParser {

    @SuppressWarnings("all")
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, InstantiationException {
        //反射获得字节码对象
        Class clazz = CustomTestDemo.class;
        //获得改字节码所有方法
        Method[] methods = clazz.getMethods();
        if(null == methods){
            return;
        }
        //遍历所有方法
        for (Method method : methods) {
            //如果该方法使用@CustomTest注解
            if(method.isAnnotationPresent(CustomTest.class)){
                //执行改方法
                method.invoke(clazz.newInstance(), null);
            }
        }
    }

}
