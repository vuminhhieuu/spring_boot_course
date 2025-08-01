package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class HelloController {

    @Autowired // Spring sẽ tự động inject một instance của UserRepository vào đây
    private UserRepository userRepository;

    @GetMapping("/")
    public String sayHello() {
        return "Hello, World!";
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    public Optional<User> getUserById(@PathVariable Long id) {
        return userRepository.findById(id);
    }

    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @GetMapping("/products")
    public String getProductsByCategory(@RequestParam(required = false) String category) {
        if (category != null) {
            return "Lay danh sach san pham thuoc danh muc: " + category;
        } else {
            return "Lay danh sach tat ca san pham";
        }
    }
}