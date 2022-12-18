package com.medium.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medium.model.Blog;
import com.medium.model.Tag;
import com.medium.model.User;
import com.medium.repository.BlogRepository;

@Service
public class BlogService {
	// Singleton Pattern
	@Autowired
	private BlogRepository blogRepository; 
	@Autowired
	private UserService userService;
	@Autowired
	private TagService tagService;

	// checks if the blog exists from the blog number then 
	// checks the user info and tag info
	public Blog createBlog(Blog blog) {
		Blog matchingBlog =  getBlogById(blog.getNo());
		User user = userService.getByName(blog.getUser().getName());
		Tag tag = tagService.getByName(blog.getTag().getTitle());
		if(tag == null)
			tag = tagService.createTag(blog.getTag());
		if(matchingBlog == null && user != null && !user.getBlogList().contains(blog) && tag != null ) {
			System.out.println("blog: " + blog);
			blog.setUser(user);
			addBUserBlog(user,blog);
			return blogRepository.createBlog(blog);
		}
		return null;
	}
	// Adds the new blog to the user's blog list
	private void addBUserBlog(User user, Blog blog) {
		List<Blog> list = user.getBlogList();
		List<Blog> blogs = new ArrayList<>(list);
		blogs.add(blog);
		user.setBlogList(blogs);
	}
	// Returns all blogs
	public List<Blog> getAllBlog(){
		System.out.println("blogs: " + blogRepository.getAllBlog());
		return blogRepository.getAllBlog();
	}
	// Returns blog by blog number
	public Blog getBlogById(Long id) {
		return blogRepository.findById(id);
	}
	// Returns blogs by user
	public List<Blog> getByUser(String person){
		return  blogRepository.getByUser(person);
	}
	// Updates blog by blog id and new blog information
	public Blog updateBlog(Long id, Blog blog) {
		Blog oldBlog =  getBlogById(id);
		System.out.println(oldBlog);
		System.out.println(id + " "  + blog);
		if(oldBlog != null && oldBlog.getUser().getName().equals(blog.getUser().getName())) {
			System.out.println(id + " "  + blog);
			return blogRepository.updateBlog(id, blog);
		}
		return null;
	}
	// Deletes blog by blog id 
	public Blog deleteBlog(Long id) {
		Blog blog = getBlogById(id);
		if(blog != null){
			return blogRepository.deleteById(blog);
		}
		return null;
	}
	// Creates a new task
	public Blog createTask(Blog task) {
		Blog matchingTask =  blogRepository.findByTaskId(task.getNo());
		User user = userService.getByName(task.getUser().getName());
		if(matchingTask == null && user != null && task.getNo() != null ) 
			return blogRepository.saveBlog(task);
		return null;
	}
	// Returns all tasks
	public List<Blog> getAllTask(){
		return blogRepository.getAllTask();
	}
	


}
