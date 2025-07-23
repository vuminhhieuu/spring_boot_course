package com.example.springcore;

import config.DataSourceConfig;
import controller.JdbcDemoController;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import repository.UserRepository;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(DataSourceConfig.class);

        UserRepository repo = new UserRepository(context.getBean("jdbcTemplate", org.springframework.jdbc.core.JdbcTemplate.class));
        JdbcDemoController controller = new JdbcDemoController(repo);

        controller.insertUser();
        controller.showAllUsers();

        context.close();
    }
}
