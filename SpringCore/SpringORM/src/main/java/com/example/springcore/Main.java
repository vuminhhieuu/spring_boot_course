package com.example.springcore;

import com.example.springcore.config.AppConfig;
import com.example.springcore.dao.StudentDao;
import com.example.springcore.entity.Student;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        StudentDao studentDao = context.getBean(StudentDao.class);

        // Thêm mới
        studentDao.save(new Student("Hieu Nguyen", 25));

        // Lấy danh sách
        List<Student> students = studentDao.findAll();
        students.forEach(System.out::println);

        context.close();
    }
}