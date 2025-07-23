package com.example.springcore.dao;

import com.example.springcore.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public class StudentDao {

    private final SessionFactory sessionFactory;

    public StudentDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public void save(Student student) {
        Session session = sessionFactory.getCurrentSession();
        session.save(student);
    }

    @Transactional
    public List<Student> findAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Student", Student.class).getResultList();
    }
}
