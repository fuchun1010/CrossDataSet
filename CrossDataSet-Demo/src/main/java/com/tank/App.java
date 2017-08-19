package com.tank;

import com.tank.utils.MySqlUtils;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        MySqlUtils mySqlUtils = MySqlUtils.instance();
        mySqlUtils.initDefaultTable();
    }
}
