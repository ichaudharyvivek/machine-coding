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
        App.setTestLombok(true);
        System.out.println( "Hello World!" );
        System.out.println(App.isTestLombok());
    }
}
