package com.brasilprev.loja.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(UsersController.API_PATH)
public class UsersController {

	static final String API_PATH = "api/user/";

	@ResponseBody
	@GetMapping
	public String getUsers() {
		return "{\"users\":[{\"name\":\"Lucas\", \"country\":\"Brazil\"},"
				+ "{\"name\":\"Jackie\",\"country\":\"China\"}]}";
	}

}