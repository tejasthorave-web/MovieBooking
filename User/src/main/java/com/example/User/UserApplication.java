package com.example.User;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);
	}

}
//APIs
//Create user
//POST /users
//
//Get all users
//GET /users
//
//Get user by id
//GET /users/{id}

//Role
//Manages customer accounts
//Used by booking-service
//Used by payment-service
//Linked with notification-service later