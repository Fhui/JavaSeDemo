package com.test.java.sql;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbUtils {

    private static String mDbDriver;
    private static String mDbUrl;
    private static String mUserName;
    private static String mPassword;
    private static Connection mConnection;

    static {
        try {
            readDbConfig();
            mConnection = DriverManager.getConnection(mDbUrl, mUserName, mPassword);
            Class.forName(mDbDriver);
        } catch (FileNotFoundException e) {
            System.out.println("配置文件未找到");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("文件读取失败");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("数据库驱动错误");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("数据库连接失败");
            e.printStackTrace();
        }
    }

    private static void readDbConfig() throws IOException {
        Reader reader = new FileReader("config.properties");
        Properties properties = new Properties();
        properties.load(reader);
        mDbDriver = properties.getProperty("driver");
        mDbUrl = properties.getProperty("url");
        mUserName = properties.getProperty("username");
        mPassword = properties.getProperty("password");
    }

    public static Connection getConnection() {
        return mConnection;

    }

}
