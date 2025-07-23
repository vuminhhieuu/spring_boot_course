package controller;

import model.User;
import repository.UserRepository;

import java.util.List;

public class JdbcDemoController {
    private final UserRepository repo;

    public JdbcDemoController(UserRepository repo) {
        this.repo = repo;
    }

    public void insertUser() {
        User user = new User(0, "Hieu", "hieu@example.com");
        repo.save(user);
        System.out.println("User inserted.");
    }

    public void showAllUsers() {
        List<User> users = repo.findAll();
        users.forEach(System.out::println);
    }
}
