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

import com.medium.model.Tag;
import com.medium.model.User;
import com.medium.service.TagService;

@RestController
@RequestMapping(value = "/tags")
public class TagController {
	
	@Autowired
	private TagService tagService;
	
	// Returns all tags
	@GetMapping
	public ResponseEntity<List<Tag>> getAll(){
		List<Tag> tags = tagService.getAllTag();
		if(tags != null)
			return new ResponseEntity<>(tags, HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	// Creates a new tag
	@PostMapping
	public ResponseEntity<Tag> create(@RequestBody Tag newTag){
		Tag tag = tagService.createTag(newTag);
		if(tag != null)
			return new ResponseEntity<>(tag, HttpStatus.CREATED);
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	// User follows a tag
	@PostMapping(value = "/follow/{tag}")
	public ResponseEntity<List<Tag>> follow(@PathVariable String tag, @RequestBody User user){
		List<Tag> tags = tagService.followTag(user, tag);
		if(tags != null)
			return new ResponseEntity<>(tags, HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	// Returns the tags the user is following
	@GetMapping(value = "/user/{user}")
	public ResponseEntity <List<Tag>> getUserTags(@PathVariable String user ) {
		List<Tag> tags = tagService.getUserTags(user);
		if(user != null)
		    return new ResponseEntity<> (tags,HttpStatus.OK);
	    return new ResponseEntity<> (HttpStatus.NO_CONTENT);
	}
	// Returns users who follow the tag
	@GetMapping(value = "/{tag}")
	public ResponseEntity <List<User>> getTagFollowers(@PathVariable String tag ) {
		List<User> users = tagService.getFollowingTag(tag);
		if(users != null)
		    return new ResponseEntity<> (users,HttpStatus.OK);
	    return new ResponseEntity<> (HttpStatus.NO_CONTENT);
	}	
}
