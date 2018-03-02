package com.test.java.sql;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class Work {

    private static Connection connection;
    private static PreparedStatement preparedStatement;

    public static void main(String[] args) throws SQLException {
        connection = DbUtils.getConnection();
        Map<String, Object> map = new HashMap<>();
        map.put("liuyan", "123456");
        map.put("wangbaoqiang", "123321");
        map.put("fangbian", "abcd");
        map.put("miejueshitai", "123abc321");
        Set<String> usernameList = map.keySet();
        for (String string : usernameList) {
            System.out.println(string);
        }
        String querySql = "SELECT  * FROM  userinfo";
        String insertSql = "INSERT INTO userinfo (username, password) VALUE (?, ?)";
        Connection connection = DbUtils.getConnection();
        try {
            PreparedStatement insertStatement = connection.prepareStatement(insertSql);
            PreparedStatement preparedStatement = connection.prepareStatement(querySql);
            ResultSet resultSet = preparedStatement.executeQuery(querySql);
            while (resultSet.next()) {
                String name = resultSet.getString(2);
                if (usernameList.contains(name)) {
                    System.out.println(name + "的信息已经存在");
                } else {
                    insertStatement.setString(1, name);
                    insertStatement.setString(2, (String) map.get(name));
                    insertStatement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        List<String> studentList = getAllStudent();
        for (String str : studentList) {
            System.out.println(str);
        }
        System.out.println("-----");
        System.out.println(getStudentById("select * from student where id = 1"));
        System.out.println(deleteStudentById("delete  from student where id = 51"));
        System.out.println(addStudent("麦当劳", "男", 99));
        System.out.println(alterStudent("update  student set name = '电风扇' where id = 65"));
    }

    public static List<String> getAllStudent() throws SQLException {
        List<String> list = new ArrayList<>();
        String sql = "SELECT  * FROM  student";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            list.add(resultSet.getString(2));
        }
        return list;
    }

    public static String getStudentById(String sql) throws SQLException {
        String student = "not found info";
        preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            student = resultSet.getString(2);
        }
        return student;
    }

    public static boolean deleteStudentById(String sql) throws SQLException {
        preparedStatement = connection.prepareStatement(sql);
        return preparedStatement.executeUpdate() == 1;
    }

    public static boolean addStudent(String name, String sex, int score) throws SQLException {
        String sql = "INSERT INTO stub.student (name, sex, score) VALUES (?, ?, ?)";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, sex);
        preparedStatement.setInt(3, score);
        return preparedStatement.executeUpdate() == 1;
    }

    public static boolean alterStudent(String sql) throws SQLException {
        preparedStatement = connection.prepareStatement(sql);
        return preparedStatement.executeUpdate() == 1;

    }

}
