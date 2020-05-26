package com.projectx.controllers;

import com.projectx.model.User;
import com.projectx.service.EmailService;
import com.projectx.service.SenderMailService;
import com.projectx.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@CrossOrigin(origins = "*")
public class UserController {

	private UserService userService;
	private EmailService emailService;
	private SenderMailService senderMailService;

	public UserController(UserService userService, EmailService emailService, SenderMailService senderMailService) {
		this.userService = userService;
		this.emailService = emailService;
		this.senderMailService = senderMailService;
	}

	@PostMapping(value = "/saveUser")
	public ResponseEntity<?> saveUser(@RequestBody User user) {
		try {
			Optional<User> userOpt = userService.validateAndSaveUser(user);

			if(userOpt.isPresent())
				return new ResponseEntity<User>(user, HttpStatus.CREATED);
			else
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("ERRO, TENTE NOVAMENTE");
		}catch (Exception e){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("ERRO, TENTE NOVAMENTE");
		}
	}

	@PostMapping(value = "/resendEmail")
	public ResponseEntity<?> resendEmail(@RequestBody User user) {
		try {
			Optional<User> userOpt = emailService.resendEmail(user);
			if(userOpt.isPresent())
				return new ResponseEntity<User>(user, HttpStatus.OK);
			else
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("EMAIL NÃO ENCONTRADO OU JÁ VALIDADO");
		}catch (Exception e){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("ERRO, TENTE NOVAMENTE");
		}
	}

	@GetMapping(value = "/vl/{hash}")
	public ResponseEntity<?> validaEmail(@PathVariable("hash")String hash) {
		try {
			System.out.println("valindado ->" + hash);
			Optional<User> userUpdated = emailService.confirmEmail(hash);

			if (userUpdated.isPresent())
				return ResponseEntity.status(HttpStatus.OK).body("Seu e-mail foi validado! Obrigado por participar!");
			else
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Esse linke está incorreto, entre em contato bla bla bla...");
		}catch (Exception e){
			return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = "/getUser/{email}")
	public ResponseEntity<?> getUser(@PathVariable("email")String email) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(userService.findUser(email));
		}
		catch (Exception e){
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = "/health")
	public ResponseEntity<?> health() {
		return new ResponseEntity(HttpStatus.OK);
	}

	@GetMapping(value = "/util")
	public ResponseEntity<?> util() throws Exception {
		// TODO ATENÇÃO -> userService.deleteAllUsers();
		return ResponseEntity.status(HttpStatus.OK).body("OK");
	}


}

