package com.tank;

import com.tank.utils.SparkUtils;


public class App 
{
    public static void main( String[] args ) throws Exception
    {
        SparkUtils sparkUtils = SparkUtils.getInstance();
        sparkUtils.createSqlContext().load().show();
    }
}
