package com.test.java.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbUtilsTest {

    public static  void main(String[] args){
        Connection connection = DbUtils.getConnection();
        String sql = "select sum(age) as getSum, name from student_testsql group by name having getSum < 20";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setString(1, age);
//            preparedStatement.setString(2, "name");
//            preparedStatement.setString(3, "name");
            ResultSet resultSet = preparedStatement.executeQuery(sql);
            while (resultSet.next()){
                System.out.println(resultSet.getInt(1)+ "---"
                        + resultSet.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
