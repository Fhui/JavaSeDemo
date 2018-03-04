package com.test.java.sql.utils;

import com.test.java.domain.Person;
import com.test.java.sql.DatabaseUtils;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class CommonDbUtilsTest {

    private static Connection mConnection;

    public static void main(String[] args) throws SQLException {
        mConnection = DatabaseUtils.getConnection();
//       insert();
//       update();
//       delete();
//       testArrayHandler();
//        testArrayListHandler();
//        testBeanHandler();
//        testBeanListHandler();
//        testColumnListHandler();
//        testScalarHandler();
//        testMapHandler();
        testMapListHandler();
    }

    /**
     * 测试 mapListHandler
     * 将结果集的每一行记录封装到map集合中
     * 将map集合封装到一个list中
     * @throws SQLException
     */
    public static void testMapListHandler() throws SQLException{
        String sql = "select *  from person";
        QueryRunner runner = new QueryRunner();
        List<Map<String, Object>> query = runner.query(mConnection, sql, new MapListHandler());
        for (Map<String, Object> map : query) {
            for(String key: map.keySet()){
                System.out.print(key + "---" + map.get(key));
            }
            System.out.println("\n");
        }
    }

    /**
     * 测试 mapHandler
     * 将结果集的第一行封装到map集合中
     * @throws SQLException sql exception
     */
    public static void testMapHandler() throws SQLException{
        String sql = "select *  from person";
        QueryRunner runner = new QueryRunner();
        Map<String, Object> map = runner.query(mConnection, sql, new MapHandler());
        for(String key : map.keySet()){
            System.out.println(key + "---" + map.get(key));
        }
    }


    /**
     * 测试 scalarHandler
     * 常用语单数据查询
     * @throws SQLException sql exception
     */
    public static void testScalarHandler() throws SQLException{
        String sql = "select count(*)  from person";
        QueryRunner runner = new QueryRunner();
        Object query = runner.query(mConnection, sql, new ScalarHandler<>());
        System.out.println(query);
    }


    /**
     *测试 columnListHandler
     * 将结果集指定的列字段值封装到一个List中
     * @throws SQLException sql exception
     */
    public static void testColumnListHandler() throws SQLException{
        String sql = "select name  from person";
        QueryRunner runner = new QueryRunner();
        List<Object> query = runner.query(mConnection, sql, new ColumnListHandler<>());
        for (Object o : query) {
            System.out.println(o);
        }

    }

    /**
     * 测试 beanListHandler
     * 将结果集的每一条记录都封装到指定的JavaBean中
     * 将这些JavaBean封装到一个List中
     * @throws SQLException sql exception
     */
    public static void testBeanListHandler() throws SQLException{
        String sql = "select * from person";
        QueryRunner runner = new QueryRunner();
        List<Person> query = runner.query(mConnection, sql, new BeanListHandler<>(Person.class));
        for (Person person : query) {
            System.out.println(person);
        }
    }

    /**
     * 测试 beanHandler
     * 将结果集的第一条记录封装到一个指定的JavaBean中
     * @throws SQLException sql exception
     */
    public static void testBeanHandler() throws SQLException{
        String sql = "select * from person";
        QueryRunner runner = new QueryRunner();
        Person person = runner.query(mConnection, sql, new BeanHandler<>(Person.class));
        System.out.println(person);
    }

    /**
     * 测试 arrayListHandler
     * 将结果集的每一条记录都封装到一个数组中
     * 将这些数组封装到一个list中
     * @throws SQLException sql exception
     */
    public static void testArrayListHandler() throws SQLException{
        String sql = "select * from person";
        QueryRunner runner = new QueryRunner();
        List<Object[]> query = runner.query(mConnection, sql, new ArrayListHandler());
        for (Object[] objects : query) {
            for (Object object : objects) {
                System.out.print(object + "\t");
            }
            System.out.println("\n");
        }
    }

    /**
     * 测试 arrayHandler
     * 将结果集的第一条记录封装到一个object数组中
     * 数组中的每一个元素就是这条记录的每一个字段的值
     * @throws SQLException sql exception
     */
    public static void testArrayHandler() throws SQLException {
        String sql = "select * from person";
        QueryRunner runner = new QueryRunner();
        Object[] query = runner.query(mConnection, sql, new ArrayHandler());
        for (Object o : query) {
            System.out.print(o + "\t");
        }
    }

    public static void update() throws SQLException {
        String sql = "update  person set name = ?,  age = ?,  sex = ? where id = ?";
        QueryRunner runner = new QueryRunner();
        int update = runner.update(mConnection, sql, "张小君", 89, "女", 1);
        System.out.println(update == 0 ? "error" : "success");

    }


    public static void delete() throws SQLException {
        String sql = "delete  from person where id = ?";
        QueryRunner runner = new QueryRunner();
        int update = runner.update(mConnection, sql, 4);
        System.out.println(update == 0 ? "error" : "success");

    }


    public static void insert() {
        String sql = "insert into person (name, age, sex) values (?, ?, ?)";
        QueryRunner runner = new QueryRunner();
        Object[] params = {"王国强", 20, "男"};
        int update = 0;
        try {
            update = runner.update(mConnection, sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DbUtils.closeQuietly(mConnection);
        System.out.println(update == 1 ? "success" : "error");
    }


}
