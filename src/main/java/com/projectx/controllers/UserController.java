package com.projectx.controllers;

import java.util.Optional;

import com.projectx.model.User;
import com.projectx.service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@RestController
public class UserController {

	private UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping(value = "/saveUser")
	public ResponseEntity<User> saveUser(@RequestBody User user) {
		try {
			userService.saveUser(user);
			return new ResponseEntity<User>(user, HttpStatus.CREATED);
		}catch (Exception e){
			return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = "/getUser/{id}")
	public ResponseEntity<?> getUser(@PathVariable("id")String cpf) {
		System.out.println("USER ID " + cpf);
		try {
			return ResponseEntity.status(HttpStatus.OK).body(userService.findUser(cpf));
		}
		catch (Exception e){
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);	
		}
	}
}

