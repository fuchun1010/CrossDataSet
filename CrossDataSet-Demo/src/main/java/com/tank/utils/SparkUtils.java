package com.tank.utils;
/*                                                                      *\
**                                                                      **
**      __  __ _________ _____          ©Mort BI                        **
**     |  \/  / () | () |_   _|         (c) 2015                        **
**     |_|\/|_\____|_|\_\ |_|           http://www.bigeyedata.com       **
**                                                                      **
\*                                                                      */


import org.apache.hadoop.yarn.webapp.hamlet.Hamlet;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.DataFrameReader;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.SparkSession;


import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class SparkUtils {

  public static SparkUtils getInstance() {
    return instance;
  }


  private static SparkUtils instance = new SparkUtils();

  private SparkUtils() {

  }

  public DataFrameReader createSqlContext() {
    SparkSession spark = SparkSession.builder().master("local[*]").appName("CrossDataSet").getOrCreate();
    Properties props = PropertyUtils.instance().getProperties("mysql.properties");
    Map<String, String> options = new HashMap<>();
    for (Map.Entry entry : props.entrySet()) {
      options.put((String) entry.getKey(), (String) entry.getValue());
    }
    return spark.read().format("jdbc").options(options);
  }

}
