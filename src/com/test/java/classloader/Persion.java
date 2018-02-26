package com.test.java.classloader;

public class Persion {

    private String mName;
    private int mAge;
    private String mSex;

    private  Persion(String mName){
        this.mName = mName;
    }

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
