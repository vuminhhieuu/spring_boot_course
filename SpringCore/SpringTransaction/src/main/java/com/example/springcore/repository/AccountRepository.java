package com.example.springcore.repository;

import com.example.springcore.entity.Account;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AccountRepository {

    @Autowired
    private SessionFactory sessionFactory;

    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    public Account findById(int id) {
        return currentSession().get(Account.class, id);
    }

    public void update(Account account) {
        currentSession().update(account);
    }
}
