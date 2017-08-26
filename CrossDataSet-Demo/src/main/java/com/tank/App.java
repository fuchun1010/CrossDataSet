package com.tank;

import com.tank.datasource.DataExtractor;
import com.tank.utils.SparkUtils;
import org.apache.spark.sql.DataFrameReader;
import org.apache.spark.sql.SparkSession;

import java.util.stream.IntStream;


public class App {
  public static void main(String[] args) throws Exception {
    SparkUtils sparkUtils = SparkUtils.getInstance();
    SparkSession sparkSession = sparkUtils.createSparSession();
    //sparkUtils.createDetaultDataFrame(sparkSession).load().show();
    DataExtractor dataExtractor = new DataExtractor("_person");
    int pageNo = 3;
    IntStream.range(1, pageNo).mapToObj(dataExtractor::genSqlWithPageNo).forEach(sql -> {
      DataFrameReader df = sparkUtils.createDataFrame(sql, sparkSession);
      System.out.println("count ---"+sql+"---->"+df.load().count());
    });
    sparkSession.stop();
  }
}
