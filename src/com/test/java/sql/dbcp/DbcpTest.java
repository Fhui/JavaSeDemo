package com.test.java.sql.dbcp;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DbcpTest {

    private static QueryRunner mRunner;
    private static Connection mConnection;

    public static void main(String[] args) throws SQLException {
        mRunner = new QueryRunner();
        mConnection = DbcpUtils.getConnection();
//        select("SELECT * FROM STUDENT");
        addStudent();
    }

    private static void select(String sql) throws SQLException {
        List<Object[]> query = mRunner.query(mConnection, sql, new ArrayListHandler());
        for (Object[] objects : query) {
            for (Object object : objects) {
                System.out.print(object + "\t");
            }
            System.out.print("\n");
        }
    }


    private static void addStudent() throws SQLException {
        Map<String, Object> map = new HashMap<>();
        map.put("林青霞", "女");
        map.put("波多野结衣", "女");
        map.put("苍井空", "女");
        map.put("小马哥", "男");
        map.put("野子", "女");
        map.put("张老三", "男");
        String sql = "INSERT INTO student (name, sex) VALUES (?, ?)";
        String query = "SELECT name, sex FROM STUDENT";
        String countSql = "SELECT COUNT(*) FROM student";
        Object count = mRunner.query(mConnection, countSql, new ScalarHandler<>());
        List<Object[]> queryHandler = mRunner.query(mConnection, query, new ArrayListHandler());
        if ((Long) count == 0) {
            for (String key : map.keySet()) {
                mRunner.update(mConnection, sql, key, map.get(key));
            }
            return;
        }
        System.out.println(count);
        for (String key : map.keySet()) {
            for (int i = 0; i < queryHandler.size(); i++) {
                Object[] objects = queryHandler.get(i);
                if (objects[0].equals(key) && objects[1].equals(map.get(key))) {
                    System.out.println(key + "记录已存在");
                    break;
                } else {
                    mRunner.update(mConnection, sql, key, map.get(key));
                    break;
                }
            }
        }
    }
}
