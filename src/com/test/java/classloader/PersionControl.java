package com.test.java.classloader;

import java.io.FileReader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Properties;

import static java.lang.Class.forName;

public class PersionControl {

    public static void main(String[] args) throws Exception {
        System.out.println(new Persion().getClass());
        System.out.println(Persion.class);
        System.out.println(forName(Persion.class.getName()));
        Class  mCLass = Class.forName(Persion.class.getName());
        Object obj = mCLass.newInstance();
//        Constructor[] constructors = mCLass.getConstructors();
//        for (Constructor constructor : constructors) {
//            System.out.println(constructor);
//        }
//        Constructor constructor = mCLass.getConstructor(String.class, int.class, String.class);
//        Object obj = constructor.newInstance("Tom", 20, "Man");
//        System.out.println(obj);
//        Constructor declaredConstructor = mCLass.getDeclaredConstructor(String.class);
//        declaredConstructor.setAccessible(true);
//        System.out.println(declaredConstructor.newInstance("Jack"));
        //修改类中的private字段属性
        Field mName = mCLass.getDeclaredField("mName");
        //设置取消权限检查即可修改是有字段
        mName.setAccessible(true);
        mName.set(obj, "Zhang");
        System.out.println(obj);
        //运行类中的无参方法
        Method nullMethod = mCLass.getMethod("play");
        nullMethod.invoke(obj);
        //运行类中的有参方法 name:方法名, paramsType:参数类型
        Method paramsMethod = mCLass.getMethod("play", String.class);
        paramsMethod.invoke(obj, "basketball");

        //反射泛型擦除
        ArrayList<String> list = new ArrayList<>();
        list.add("hello");
        list.add("world");
        list.add("!");
        Class<? extends ArrayList> listClass = list.getClass();
        Method addMethod = listClass.getMethod("add", Object.class);
        addMethod.invoke(list, 1);
        addMethod.invoke(list, 2);
        addMethod.invoke(list, 3);
        for (Object object : list) {
            System.out.println(object);
        }

        //反射读取文件存储
        FileReader fileReader = new FileReader("config.properties");
        Properties properties = new Properties();
        properties.load(fileReader);
        fileReader.close();
        String className = properties.getProperty("className");
        String methodName = properties.getProperty("methodName");
        Class fileClass = Class.forName(className);
        Object fileObj = fileClass.newInstance();
        Method method = fileClass.getMethod(methodName, String.class);
        method.invoke(fileObj, "football");


    }
}
