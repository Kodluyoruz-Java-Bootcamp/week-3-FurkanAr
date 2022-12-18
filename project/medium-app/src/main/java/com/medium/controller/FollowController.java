package com.medium.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medium.model.User;
import com.medium.service.FollowService;


@RestController
@RequestMapping(value = "/follow")
public class FollowController {
	
	@Autowired
	private FollowService followService;
	
	// Pathvariable user you want to follow requestbody is user make follow
	// Returns follows
	@PostMapping (value = "/{userTo}")
	public ResponseEntity <List<User>> create(@PathVariable String userTo, @RequestBody  User userFrom){
		List<User> followed = followService.follow(userFrom, userTo);
		if(followed != null)
			return new ResponseEntity<>(followed, HttpStatus.CREATED);
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	// Returns users followed by the user
	@GetMapping (value = "/{userFrom}")
	public ResponseEntity <List<User>> getFollowed(@PathVariable String userFrom){
		List<User> followed = followService.getFollowed(userFrom);
		if(followed != null)
			return new ResponseEntity<>(followed, HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	// Returns users following the user
	@GetMapping (value = "/follower/{user}")
	public ResponseEntity <List<User>> getFollowers(@PathVariable String user){
		List<User> followers = followService.getFollowers(user);
		if(followers != null)
			return new ResponseEntity<>(followers, HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
