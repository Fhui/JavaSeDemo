package com.test.java.sql;

import java.sql.*;

public class TestSql {


    public static void main(String args[]) {
        //设置数据库连接的url
        String url = "jdbc:mysql://localhost:3306/test_sql?useSSL=false";
        try {
            //获取Connection对象
            Connection conn = DriverManager.getConnection(url, "root", "123456");
            //注册驱动
            Class.class.forName("com.mysql.jdbc.Driver");
            //获取Statement平台对象
            Statement statement = conn.createStatement();
            String sql1 = "insert into person (name) values (?)";
            //获得预处理对象, 防止sql注入
            PreparedStatement preparedStatement = conn.prepareStatement(sql1);
            preparedStatement.setString(1, "布莱恩");
            preparedStatement.executeUpdate();
            //sql语句
            String sql = "select * from person";
            //查询, 获得结果集
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                System.out.println(resultSet.getInt(1) + "---" + resultSet.getString(2));
            }
            //释放资源
            resultSet.close();
            statement.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
