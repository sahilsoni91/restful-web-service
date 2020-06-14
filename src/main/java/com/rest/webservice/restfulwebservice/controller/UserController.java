package com.rest.webservice.restfulwebservice.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rest.webservice.restfulwebservice.bean.User;
import com.rest.webservice.restfulwebservice.dao.UserDaoService;

@RestController
public class UserController {

	@Autowired
	private UserDaoService userService;
	
	@GetMapping(path = "/users")
	public List<User> retrieveAllUsers() {
		return userService.findAll();
	}
	
	@GetMapping(path = "/users/{id}")
	public User retrieveUser(@PathVariable int id) {
		return userService.findOne(id);
	}
	
	@PostMapping(path = "/users")
	public ResponseEntity<Object> createUser(@RequestBody User user) {
		User savedUser = userService.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
}
