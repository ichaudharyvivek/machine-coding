package com.snakeandladder;

import lombok.Getter;
import lombok.Setter;

/**
 * Hello world!
 *
 */
public class App 
{
    @Getter
    @Setter
    public static boolean testLombok;

    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        App.setTestLombok(true);
        System.out.println(App.isTestLombok());
    }
}
