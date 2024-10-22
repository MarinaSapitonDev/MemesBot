package com.example.memesbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class MemesBotApplication {
  //  private static ApplicationContext applicationContext;

    public static void main(String[] args) {
        SpringApplication.run(MemesBotApplication.class, args);
       /* applicationContext =
                new AnnotationConfigApplicationContext(MemesBotApplication.class);
        for (String beanName : applicationContext.getBeanDefinitionNames()) {
            System.out.println(beanName);
        }*/
    }

}
