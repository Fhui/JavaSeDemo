package com.test.java.sql.dbcp;

import org.apache.commons.dbcp2.BasicDataSource;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DbcpUtils {

    private static BasicDataSource dataSource ;
    private static String mUserName;
    private static String mPwd;
    private static String mDriver;
    private static String mUrl;

    static {
        loadDbConfig();
        dataSource = new BasicDataSource();
        dataSource.setUrl(mUrl);
        dataSource.setDriverClassName(mDriver);
        dataSource.setUsername(mUserName);
        dataSource.setPassword(mPwd);
    }

    private static void loadDbConfig(){
        Properties properties = new Properties();
        try {
            FileReader reader = new FileReader("config.properties");
            properties.load(reader);
            mUserName = properties.getProperty("username");
            mPwd = properties.getProperty("password");
            mDriver = properties.getProperty("driver");
            mUrl = properties.getProperty("url");
        } catch (FileNotFoundException e) {
           throw new RuntimeException("文件未找到");
        } catch (IOException e) {
            throw new RuntimeException("加载数据库配置文件失败");
        }
    }

    public static Connection getConnection(){
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("数据库连接失败");
        }
    }

}
