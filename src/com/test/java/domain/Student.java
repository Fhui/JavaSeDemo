package com.test.java.domain;

public class Student {
    private int id;
    private String classname;
    private int score;
    private String name;
    private String sex;

    public Student() {
    }

    public Student(int id, String classname, int score, String name, String sex) {
        this.id = id;
        this.classname = classname;
        this.score = score;
        this.name = name;
        this.sex = sex;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", classname='" + classname + '\'' +
                ", score=" + score +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
}
