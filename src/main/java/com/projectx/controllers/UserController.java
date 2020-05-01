package com.projectx.controllers;

import com.projectx.model.User;
import com.projectx.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
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

	@GetMapping(value = "/getUser/{document}")
	public ResponseEntity<?> getUser(@PathVariable("document")String cpf) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(userService.findUser(cpf));
		}
		catch (Exception e){
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);	
		}
	}
}

