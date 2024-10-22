package com.sapiton.memesbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
