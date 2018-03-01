package com.test.java.sql;

import java.sql.*;

public class ControlSql {

    private static Connection connection = null;
    private static Savepoint savepoint = null;

    public static void main(String[] args) throws SQLException {
        // MySQL的JDBC URL编写方式：jdbc:mysql://主机名称：连接端口/数据库的名称?参数=值
        // 避免中文乱码要指定useUnicode和characterEncoding
        String url = "jdbc:mysql://localhost:3306/test_sql?"
                + "user=root&password=123456&useUnicode=true&characterEncoding=UTF8&useSSL=false";
        try {
            connection = DriverManager.getConnection(url);
            //加载mysql驱动
            Class.forName("com.mysql.jdbc.Driver");
            Statement statement = connection.createStatement();
            //创建数据库表格
//            String addTableSql = "create table person (id int primary key,  name varchar(20))";
//            int result = statement.executeUpdate(addTableSql);
            //数据库插入数据
//            String insertSql = "insert into person (id, name) values ('2', 'Lucy')";
//            int result = statement.executeUpdate(insertSql);
//            System.out.println(result == -1 ? "error" : "success");
            //数据库查询数据
//            String querySql = "select * from Person";
//            ResultSet resultSet = statement.executeQuery(querySql);
//            while (resultSet.next()) {
//                System.out.println(resultSet.getString(1) + "---" + resultSet.getString(2));
//            }
            //相当于 start transaction 开启事务
//            connection.setAutoCommit(false);
//            String updateSql = "update test set money = money - 100 where name = 'Tom'";
//            int execute = statement.executeUpdate(updateSql);
//            System.out.println(execute == -1 ? "error" : "success");
//            connection.commit();
            String tomSql = "update test set money = money - 1000 where name = 'Tom'";
            String lucySql = "update test set money = money  + 1000 where name = 'lucy'";
            connection.setAutoCommit(false);
            statement.executeUpdate(tomSql);
            //设置事务回滚点
            savepoint = connection.setSavepoint();
            //模拟运行异常走到Exception中
//            int i = 5 / 0;
            statement.executeUpdate(lucySql);
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
            connection.rollback(savepoint);
            System.out.println("事务回滚成功");
            connection.commit();
            System.out.println("事务提交成功");
        }
    }

}
