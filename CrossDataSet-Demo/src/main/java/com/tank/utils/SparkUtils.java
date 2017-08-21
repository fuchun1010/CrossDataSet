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


import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.Properties;
import java.util.stream.IntStream;

public class SparkUtils {

    public Optional<Dataset> innerJoinTwoDataSet(String tableA, String tableB) throws  Exception{
      Optional<Dataset> opt = Optional.empty();
      SQLContext sqlContext = this.createSqlContext();
      Properties prop = PropertyUtils.instance().getProperties("mysql.properties");
      String url = prop.getProperty("mysql.url");
      //TODO manual map reduce data
      long rowsA = 5000000;
      long rowsB = 7;
      long unit = 500;

      while(rowsA > 500) {


      }

      return opt;
    }

    public Dataset create() throws  Exception{
        Properties prop = PropertyUtils.instance().getProperties("mysql.properties");


        SQLContext sql = this.createSqlContext();

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


    private SQLContext createSqlContext() throws  Exception{
      Properties prop = PropertyUtils.instance().getProperties("mysql.properties");
      SparkConf conf = new SparkConf().setMaster("local[*]").setAppName("CrossDataSet");
      SparkContext sc = new SparkContext(conf);
      Class.forName(prop.getProperty("mysql.driver"));
      SQLContext sql = new SQLContext(sc);

      return sql;
    }

    private static  SparkUtils instance = new SparkUtils();

    private  SparkUtils() {

    }
}
