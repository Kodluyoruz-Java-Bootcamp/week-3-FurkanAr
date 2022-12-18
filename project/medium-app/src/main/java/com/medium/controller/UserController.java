package com.medium.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medium.model.User;
import com.medium.service.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	// Returns all users
	@GetMapping
	public ResponseEntity<List<User>> getAll(){
		List<User> users = userService.getAllUser();
		if(users != null)
			return new ResponseEntity<>(users, HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	// Returns added user
	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User newUser){
		User user = userService.createUser(newUser);
		if(user != null)
			return new ResponseEntity<>(user, HttpStatus.CREATED); 
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST); 
	}
	// Updates user by name
	@PutMapping(value = "/update/{name}")
	public ResponseEntity<User> updatePassword(@PathVariable String name,@RequestBody User newUser) {
		User user = userService.updatePassword(name, newUser);
		if(user != null)
		    return new ResponseEntity<> (user,HttpStatus.OK);
	    return new ResponseEntity<> (HttpStatus.NOT_FOUND);
	}
	// Returns user by name
	@GetMapping(value = "/{name}")
	public ResponseEntity<User> getByName(@PathVariable String name) {
		User user = userService.getByName(name);
		if(user != null)
		    return new ResponseEntity<> (user,HttpStatus.OK);
	    return new ResponseEntity<> (HttpStatus.NOT_FOUND);
	}
	
}
