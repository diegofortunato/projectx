package com.projectx.controllers;

import com.projectx.model.User;
import com.projectx.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	private UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping(value = "/saveUser")
	public ResponseEntity<User> saveUser(@RequestBody User user) {
		try {
			userService.validateAndSaveUser(user);
			return new ResponseEntity<User>(user, HttpStatus.CREATED);
		}catch (Exception e){
			return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

