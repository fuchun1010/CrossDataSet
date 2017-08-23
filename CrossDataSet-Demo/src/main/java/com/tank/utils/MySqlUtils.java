package com.tank.utils;/*                                                                      *\
**                                                                      **
**      __  __ _________ _____          ©Mort BI                        **
**     |  \/  / () | () |_   _|         (c) 2015                        **
**     |_|\/|_\____|_|\_\ |_|           http://www.bigeyedata.com       **
**                                                                      **
\*                                                                      */

import java.sql.*;
import java.util.Optional;
import java.util.Properties;
import java.util.Random;
import java.util.stream.IntStream;

public class MySqlUtils {

    public static MySqlUtils instance() {
        return instance;
    }

    public  void initDefaultTable(boolean init ) {
        if(!init) {
            return;
        }
        Optional<Connection> opt = this.createConn();
        Properties prop = PropertyUtils.instance().getProperties("mysql.properties");
        String[] locations = {"北京", "上海", "广州", "深圳", "重庆", "四川", "云南"};
        if(!opt.isPresent())
            return;

        try(Connection conn = opt.get()) {
            String table = prop.getProperty("mysql.table.location");
            int maxRows = locations.length;
            String sql = "insert into " + table + " ( _name ) values(?)";
            final PreparedStatement ps = conn.prepareStatement(sql);
            IntStream.range(0, maxRows).forEach( i -> {
                try{
                    ps.setString(1, locations[i]);
                    ps.addBatch();
                    ps.clearParameters();
                }
                catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            });
            int[] batch = ps.executeBatch();
            System.out.println("batch size is:" + batch);
            ps.clearBatch();
            ps.close();

            table = prop.getProperty("mysql.table.person");
            maxRows = Integer.parseInt(prop.getProperty("mysql.table.person.max_rows"));
            sql = "insert into " + table + " ( _name, _salory, _location_id) values(?, ?, ?)";
            Integer[] salaries = {1000,1500,2300,5300};
            Integer[] locationsIds = {1,2,3,4};
            final PreparedStatement p2 = conn.prepareStatement(sql);
            IntStream.range(0, maxRows).forEach(i -> {
                try{
                    int salary = this.random(salaries);
                    int locationId = this.random(locationsIds);
                    p2.setString(1, "name_" + i);
                    p2.setInt(2, salary);
                    p2.setInt(3, locationId);
                    p2.addBatch();
                }
                catch (SQLException e) {
                    System.out.print(e.getMessage());
                }
            });
            p2.executeBatch();
            p2.close();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private<T> T random(T[] arr) {
        Random random = new Random();
        int index  = random.nextInt(arr.length);
        return arr[index];
    }

    private Optional<Connection> createConn() {
        Optional<Connection> opt = Optional.empty();
        Properties prop = PropertyUtils.instance().getProperties("mysql.properties");
        try{
            String driver = prop.getProperty("driver");
            String url = prop.getProperty("url");
            String user = prop.getProperty("user");
            String password = prop.getProperty("password");
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, user, password);
            return opt.of(conn);
        }
        catch (ClassNotFoundException | SQLException e) {
            System.out.print(e.toString());
        }
        return opt;
    }

    private static MySqlUtils instance = new MySqlUtils();

    private MySqlUtils() {

    }

}
