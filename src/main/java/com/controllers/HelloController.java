package com.projectx;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// @Controller
// @RequestMapping("hello")
@RestController
public class HelloController {

	@GetMapping(value="/a")
	public String index() {
		return "Greetings from Spring Boot!";
	}

}

