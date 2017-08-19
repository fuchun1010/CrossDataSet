package com.tank.utils;/*                                                                      *\
**                                                                      **
**      __  __ _________ _____          ©Mort BI                        **
**     |  \/  / () | () |_   _|         (c) 2015                        **
**     |_|\/|_\____|_|\_\ |_|           http://www.bigeyedata.com       **
**                                                                      **
\*                                                                      */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtils {

    public  static  PropertyUtils propertyUtils = new PropertyUtils();

    public  static  PropertyUtils instance() {
        return propertyUtils;
    }

    public Properties getProperties(String path) {
        Properties prop = new Properties();

        //TODO consider avoid duplicate properties to memory
        try{
            prop.load(ClassLoader.getSystemClassLoader().getResourceAsStream(path));
        }
        catch (IOException e) {

            System.out.println(e.toString());
        }
        return prop;
    }

    private PropertyUtils() {

    }
}
