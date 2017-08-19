package com.tank.utils;/*                                                                      *\
**                                                                      **
**      __  __ _________ _____          ©Mort BI                        **
**     |  \/  / () | () |_   _|         (c) 2015                        **
**     |_|\/|_\____|_|\_\ |_|           http://www.bigeyedata.com       **
**                                                                      **
\*                                                                      */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MySqlUtils {

    public static MySqlUtils instance() {
        return instance;
    }

    public void dropTableIfExists(String tableName) {
        this.createConn();
    }

    public  void createTable(String table) {
        this.createConn();
    }

    public  void initDefaultTable() {
        String table = "";
        this.dropTableIfExists(table);
        this.createTable(table);

    }

    private   Connection createConn() {
        Connection conn = null;
        //TODO not correct mysql connection

        Properties prop = PropertyUtils.instance().getProperties("mysql.properties");
        try{
            String driver = prop.getProperty("mysql.driver");
            String url = prop.getProperty("mysql.url");
            String user = prop.getProperty("mysql.user");
            String password = prop.getProperty("mysql.password");
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
            return conn;
        }
        catch (ClassNotFoundException | SQLException e) {
            System.out.print(e.toString());
        }
        return conn;
    }

    private static MySqlUtils instance = new MySqlUtils();

    private MySqlUtils() {

    }

}
