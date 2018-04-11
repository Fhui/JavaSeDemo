package com.test.java.sql.dbcp;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

public class DbcpTest {

    private static QueryRunner mRunner;
    private static Connection mConnection;

    public static void main(String[] args) throws SQLException {
        mRunner = new QueryRunner();
        mConnection = DbcpUtils.getConnection();
//        select("SELECT * FROM STUDENT");
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



}
