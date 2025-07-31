package com.example.demo;

import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {

    @GetMapping("/")
    public String sayHello() {
        return "Hello, World!";
    }

    @GetMapping("users/{id}")
    public String getUserById(@PathVariable long id) {
        return "Lay thon tin cho user co ID: " + id;
    }

    @GetMapping("/products")
    public String getProductsByCategory(@RequestParam(required = false) String category) {
        if (category != null) {
            return "Lay danh sach san pham thuoc danh muc: " + category;
        } else {
            return "Lay danh sach tat ca san pham";
        }
    }

    @PostMapping("/users")
    public String createUser(@RequestBody User user) {
        return "Da tao user voi ten dang nhap: " + user.getUsername() + " va mat khau: " + user.getPassword();
    }
}