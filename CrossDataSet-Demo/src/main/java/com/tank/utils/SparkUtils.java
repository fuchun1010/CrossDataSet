package com.tank.utils;
/*                                                                      *\
**                                                                      **
**      __  __ _________ _____          ©Mort BI                        **
**     |  \/  / () | () |_   _|         (c) 2015                        **
**     |_|\/|_\____|_|\_\ |_|           http://www.bigeyedata.com       **
**                                                                      **
\*                                                                      */


import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SQLContext;


import java.util.Properties;

public class SparkUtils {

    public Dataset create() throws  Exception{
        Properties prop = PropertyUtils.instance().getProperties("mysql.properties");

        SparkConf conf = new SparkConf().setMaster("local[*]").setAppName("CrossDataSet");
        SparkContext sc = new SparkContext(conf);
        Class.forName(prop.getProperty("mysql.driver"));
        SQLContext sql = new SQLContext(sc);

        Properties connectProps = new Properties();
        String url = prop.getProperty("mysql.url");
        String person = prop.getProperty("mysql.table.person");
        connectProps.put("user", prop.getProperty("mysql.user"));
        connectProps.put("password", prop.getProperty("mysql.password"));

        return sql.read().jdbc(url, person, connectProps);
    }

    public  static SparkUtils getInstance() {
        return instance;
    }

    private static  SparkUtils instance = new SparkUtils();

    private  SparkUtils() {

    }
}
