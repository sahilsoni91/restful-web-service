package com.rest.webservice.restfulwebservice.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rest.webservice.restfulwebservice.bean.User;
import com.rest.webservice.restfulwebservice.customexception.UserNotFoundException;
import com.rest.webservice.restfulwebservice.dao.UserDaoService;

@RestController
public class UserController {

	@Autowired
	private UserDaoService userService;
	
	@GetMapping(path = "/users")
	public List<User> retrieveAllUsers() {
		List<User> users = userService.findAll();
		for(User user : users) {
			Link link = WebMvcLinkBuilder.linkTo(UserController.class).slash(user.getId()).withSelfRel();
			user.add(link);
		}
		return users;
	}
	
	@GetMapping(path = "/users/{id}")
	public User retrieveUser(@PathVariable int id) {
		User user = userService.findOne(id);
		if(user == null) {
			throw new UserNotFoundException("id : " + id);
		}
		return user;
	}
	
	@PostMapping(path = "/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser = userService.save(user);
		/*
		 * If only CREATED status code to be returned in Response
		 */
//		return ResponseEntity.status(HttpStatus.CREATED).build();
		
		/*
		 * If URI of added resource to send in Response with CREATED status code
		 */
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping(path = "/users/{id}")
	public void deleteUser(@PathVariable int id) {
		User user = userService.deleteById(id);
		if(user == null) {
			throw new UserNotFoundException("id : " + id);
		}
	}
}
