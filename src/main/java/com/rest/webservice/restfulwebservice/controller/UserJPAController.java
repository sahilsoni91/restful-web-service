package com.rest.webservice.restfulwebservice.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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

import com.rest.webservice.restfulwebservice.bean.Post;
import com.rest.webservice.restfulwebservice.bean.User;
import com.rest.webservice.restfulwebservice.customexception.UserNotFoundException;
import com.rest.webservice.restfulwebservice.dao.PostRepository;
import com.rest.webservice.restfulwebservice.dao.UserDaoService;
import com.rest.webservice.restfulwebservice.dao.UserRepository;

@RestController
public class UserJPAController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@GetMapping(path = "jpa/users")
	public List<User> retrieveAllUsers() {
		List<User> users = userRepository.findAll();
		for(User user : users) {
			Link link = WebMvcLinkBuilder.linkTo(UserJPAController.class).slash(user.getId()).withSelfRel();
			user.add(link);
		}
		return users;
	}
	
	@GetMapping(path = "jpa/users/{id}")
	public User retrieveUser(@PathVariable int id) {
		/*
		 * Optional returns a proper object
		 */
		Optional<User> user = userRepository.findById(id);
		if(!user.isPresent()) {
			throw new UserNotFoundException("id : " + id);
		}
		return user.get();
	}
	
	@PostMapping(path = "jpa/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser = userRepository.save(user);
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
	
	@DeleteMapping(path = "jpa/users/{id}")
	public void deleteUser(@PathVariable int id) {
		userRepository.deleteById(id);
	}
	
	@PostMapping(path = "jpa/users/{id}/posts")
	public ResponseEntity<Object> createUser(@PathVariable int id, @RequestBody Post post) {
		Optional<User> userOptional = userRepository.findById(id);
		
		if(!userOptional.isPresent()) {
			throw new UserNotFoundException("id : " + id);
		}
		
		User user = userOptional.get();
		
		post.setUser(user);
		
		Post savedPost = postRepository.save(post);
		/*
		 * If only CREATED status code to be returned in Response
		 */
//		return ResponseEntity.status(HttpStatus.CREATED).build();
		
		/*
		 * If URI of added resource to send in Response with CREATED status code
		 */
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedPost.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@GetMapping(path = "jpa/users/{id}/posts")
	public List<Post> retrieveAllPosts(@PathVariable int id) {
		Optional<User> userOptional = userRepository.findById(id);
		
		if(!userOptional.isPresent()) {
			throw new UserNotFoundException("id : " + id);
		}
		
		return userOptional.get().getPosts();
	}
}
