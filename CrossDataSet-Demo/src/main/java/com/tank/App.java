package com.tank;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        new Thread(() -> System.out.println("Thread ouput")).start();
        System.out.println( "Hello CrossDataSet!" );
    }
}
