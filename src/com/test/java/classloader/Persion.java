package com.test.java.classloader;

import java.io.Serializable;

public class Persion implements Serializable{

    private String mName;
    private int mAge;
    private String mSex;

    public Persion(){

    }

    public Persion(String mName, int mAge, String mSex) {
        this.mName = mName;
        this.mAge = mAge;
        this.mSex = mSex;
    }

    public void play(){
        System.out.println("play games !");
    }

    public void play(String game){
        System.out.println("play "+game+" !");
    }

    @Override
    public String toString() {
        return "Persion{" +
                "mName='" + mName + '\'' +
                ", mAge=" + mAge +
                ", mSex='" + mSex + '\'' +
                '}';
    }
}
