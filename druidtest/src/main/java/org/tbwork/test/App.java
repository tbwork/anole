package org.tbwork.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * Hello world!
 *
 */
@SpringBootApplication(scanBasePackages = "org.tbwork.test")
public class App 
{



    public static void main( String[] args )
    {
        ApplicationContext run =  SpringApplication.run(App.class, args);
        System.out.println("Started.");

        DruidTest test = run.getBean(DruidTest.class);


        for(int i = 0 ; i < 10000; i++){
            test.runTest(i);
        }

        System.out.println("DONE!!!");

    }
}
