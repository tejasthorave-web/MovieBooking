package com.example.User;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/*
Registration/login and Profile management

POST http://localhost:8080/users/register -> User registration
POST http://localhost:8080/users/login -> Login + JWT
GET /api/users/{id} -> Get profile
GET /api/users/{id} -> Update profile
*/
@SpringBootApplication
public class UserApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);
	}

}
