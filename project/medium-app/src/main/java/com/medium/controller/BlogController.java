package com.medium.controller;

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

import com.medium.model.Blog;
import com.medium.service.BlogService;

@RestController
@RequestMapping(value = "/blogs")
public class BlogController {
	
	@Autowired
	private BlogService blogService;
	
	// Returns all blogs
	@GetMapping
	public ResponseEntity<List<Blog>> getAll(){
		List<Blog> blogs = blogService.getAllBlog();
		if(blogs != null)
			return new ResponseEntity<>(blogs, HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	// Create new blog and return added blog
	@PostMapping 
	public ResponseEntity<Blog> create(@RequestBody Blog newBlog){
		Blog blog = blogService.createBlog(newBlog);
		if(blog != null)
			return new ResponseEntity<>(blog, HttpStatus.CREATED);
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	// Returns a blog by blog id
	@GetMapping(value = ("/{id}"))
	public ResponseEntity<Blog> getById(@PathVariable Long id){
		Blog blog = blogService.getBlogById(id);
		if(blog != null)
			return new ResponseEntity<>(blog, HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	// Deletes blog by given id
	@DeleteMapping(value = ("/{id}"))
	public ResponseEntity<Blog> deleteBlog(@PathVariable Long id){
		Blog blog = blogService.deleteBlog(id);
		if(blog != null)
			return new ResponseEntity<>(blog, HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	// Updates blog by given id
	@PutMapping(value = ("/{id}"))
	public ResponseEntity<Blog> updateBlog(@PathVariable Long id, @RequestBody Blog updateBlog){
		Blog blog = blogService.updateBlog(id, updateBlog);
		if(blog != null)
			return new ResponseEntity<>(blog, HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	// Returns user's blogs
	@GetMapping(value = ("/user/{name}"))
	public ResponseEntity <List<Blog>> getUserBlog(@PathVariable String name){
		List<Blog> blogs = blogService.getByUser(name);
		if(blogs != null)
			return new ResponseEntity<>(blogs, HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	// Creates new task and returns it
	@PostMapping(value = ("/task"))
	public ResponseEntity<Blog> createTask(@RequestBody Blog newTask){
		Blog blog = blogService.createTask(newTask);
		if(blog != null)
			return new ResponseEntity<>(blog, HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	// Returns all tasks
	@GetMapping(value = ("/task"))
	public ResponseEntity <List<Blog>> getTasks(){
		List<Blog> tasks = blogService.getAllTask();
		if(tasks != null)
			return new ResponseEntity<>(tasks, HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	
}
