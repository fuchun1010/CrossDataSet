package com.tank;

import com.tank.utils.MySqlUtils;
import com.tank.utils.SparkUtils;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception
    {
        MySqlUtils mySqlUtils = MySqlUtils.instance();
        /*第一次需要初始化数据库，以后运行不需要*/
        boolean needStart = false;
        mySqlUtils.initDefaultTable(needStart);

        SparkUtils sparkUtils = SparkUtils.getInstance();
        sparkUtils.create().limit(1).toDF().printSchema();

    }
}
