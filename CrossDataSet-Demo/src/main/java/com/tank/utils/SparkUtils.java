package com.tank.utils;
/*                                                                      *\
**                                                                      **
**      __  __ _________ _____          ©Mort BI                        **
**     |  \/  / () | () |_   _|         (c) 2015                        **
**     |_|\/|_\____|_|\_\ |_|           http://www.bigeyedata.com       **
**                                                                      **
\*                                                                      */


import org.apache.spark.sql.DataFrameReader;
import org.apache.spark.sql.SparkSession;


import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

public class SparkUtils {

  public static SparkUtils getInstance() {
    return instance;
  }

  public SparkSession createSparSession() {
    SparkSession sparkSession = SparkSession.builder().master("local[*]").appName("CrossDataSet").getOrCreate();
    return sparkSession;
  }

  public synchronized  DataFrameReader createDataFrame(String sql, SparkSession sparkSession) {
    this.options.remove("dbtable");
    this.options.putIfAbsent("dbtable", sql);
    return sparkSession.read().format("jdbc").options(this.options);
  }

  public DataFrameReader createDetaultDataFrame(SparkSession spark) {

    return spark.read().format("jdbc").options(this.options);
  }

  private SparkUtils() {
    this.options = this.initMySQlConfig();
  }

  private ConcurrentHashMap<String, String> initMySQlConfig() {
    Properties props = PropertyUtils.instance().getProperties("mysql.properties");
    ConcurrentHashMap<String, String> options = new ConcurrentHashMap<>();
    for (Map.Entry entry : props.entrySet()) {
      options.put((String) entry.getKey(), (String) entry.getValue());
    }
    return options;
  }

  private static SparkUtils instance = new SparkUtils();

  private ConcurrentHashMap<String, String> options = null;

}
