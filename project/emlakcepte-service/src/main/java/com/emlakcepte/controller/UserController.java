package com.emlakcepte.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emlakcepte.model.User;
import com.emlakcepte.service.UserService;

@RestController
@RequestMapping(value="/users")
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
	public ResponseEntity<User> createUser(@RequestBody User newUser) {
		User user = userService.createUser(newUser);
		if(user != null) 
			return new ResponseEntity<>(user, HttpStatus.CREATED); 
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST); 
	}
	// Returns user by name
	@GetMapping(value = "/name/{name}")
	public ResponseEntity<User> getByName(@PathVariable String name) {
		User user = userService.getByName(name);
		if(user != null)
		    return new ResponseEntity<> (user,HttpStatus.OK);
	    return new ResponseEntity<> (HttpStatus.NOT_FOUND);
	}
	// Returns user by email
	@GetMapping(value = "/email/{email}")
	public ResponseEntity<User> getByEmail(@PathVariable String email) {
		User user = userService.getByEmail(email);
		if(user != null)
		    return new ResponseEntity<> (user,HttpStatus.OK);
	    return new ResponseEntity<> (HttpStatus.NOT_FOUND);
	}
	// Updates user by email
	@PutMapping(value = "/update/{email}")
	public ResponseEntity<User> updatePassword(@PathVariable String email,@RequestBody User newUser) {
		User user = userService.updatePassword(email, newUser);
		if(user != null)
		    return new ResponseEntity<> (user,HttpStatus.OK);
	    return new ResponseEntity<> (HttpStatus.NOT_FOUND);
	}
	// Deletes user by email
	@DeleteMapping(value = ("/delete/{email}"))
	public ResponseEntity<User> deleteUser(@PathVariable String email){
		User user = userService.deleteUser(email);
		if(user != null)
			return new ResponseEntity<> (user,HttpStatus.OK);
	    return new ResponseEntity<> (HttpStatus.NOT_FOUND);
	}
}
