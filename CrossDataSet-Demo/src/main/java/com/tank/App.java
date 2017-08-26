package com.tank;

import com.tank.utils.SparkUtils;
import org.apache.spark.sql.SparkSession;


public class App {
  public static void main(String[] args) throws Exception {
    SparkUtils sparkUtils = SparkUtils.getInstance();
    SparkSession sparkSession = sparkUtils.createSparSession();
    sparkUtils.createDetaultDataFrame(sparkSession).load().show();
  }
}
