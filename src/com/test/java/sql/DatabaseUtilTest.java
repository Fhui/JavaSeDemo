package com.test.java.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseUtilTest {

    public static void main(String[] args){
        Connection connection = DatabaseUtils.getConnection();
        String sql = "select * from person";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery(sql);
            while (resultSet.next()){
                System.out.println(resultSet.getInt(1)+resultSet.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
