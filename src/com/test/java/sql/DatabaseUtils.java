package com.test.java.sql;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseUtils {

    private static String mUrl;
    private static String mDriver;
    private static String mUserName;
    private static String mPassword;
    private static Connection mConnection;

    static {
        try {
            readConfig();
            mConnection = DriverManager.getConnection(mUrl, mUserName, mPassword);
            Class.forName(mDriver);
        } catch (SQLException e) {
            System.out.println("数据库连接失败");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("数据库驱动错误");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("文件读取失败");
            e.printStackTrace();
        }
    }

    private static void readConfig() throws IOException {
        FileReader reader = new FileReader("config.properties");
        Properties properties = new Properties();
        properties.load(reader);
        mUrl = properties.getProperty("url");
        mDriver = properties.getProperty("driver");
        mUserName = properties.getProperty("username");
        mPassword = properties.getProperty("password");
        reader.close();
    }

    public static Connection getConnection(){
        return mConnection;
    }

}
