package com.demo.pra.web.springbean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class test {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        UserEntity myBean = (UserEntity) context.getBean("myBean");
        System.out.println(myBean.toString());
    }

}
