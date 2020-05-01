package com.projectx.controllers;

import com.projectx.model.User;
import com.projectx.service.MongoDbService;
import com.projectx.service.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@AllArgsConstructor
@NoArgsConstructor
public class UserController {

	private UserService userService;

	@PostMapping(value = "/saveUser")
	public ResponseEntity saveUser(@RequestBody User user) {
		try {
			userService.saveUser(user);
			return new ResponseEntity(HttpStatus.CREATED);
		}catch (Exception e){
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

