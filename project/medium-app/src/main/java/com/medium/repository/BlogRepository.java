package com.medium.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.medium.model.Blog;
import com.medium.model.Status;

@Repository
public class BlogRepository {
	// List of all blogs
	private static List<Blog> blogList = new ArrayList<>();
	// List of all task
	private static List<Blog> taskList = new ArrayList<>();

	// Adds the new blog to the list
	public Blog createBlog(Blog blog) {
		blog.setPublishedDate(LocalDateTime.now());
		blog.setStatus(Status.ACTIVE);
		System.out.println("blogrepo: " + blog);
		blogList.add(blog);
		return blog;
	}
	// Returns all blogs
	public List<Blog> getAllBlog(){
		return blogList;
	}
	// Finds blog by id
	public Blog findById(Long id) {
		return getAllBlog()
				.stream()
				.filter(blog -> blog.getNo().equals(id))
				.findFirst()
				.orElse(null);
	}
	// Updates blog by given id
	public Blog updateBlog(Long id, Blog blog) { 
		Blog oldBlog =  findById(id);
		oldBlog.setContent(blog.getContent());
		oldBlog.setImage(blog.getImage());
		oldBlog.setStatus(blog.getStatus());
		oldBlog.setTag(blog.getTag());
		oldBlog.setTitle(blog.getTitle());
		return oldBlog;
	}
	// Deletes blog by id
	public Blog deleteById(Blog blog) {
		getAllBlog().remove(blog);
		return blog;
	}
	// Adds new task and save it
	public Blog saveBlog(Blog task) {
		taskList.add(task);
		return task;
	}
	// Returns all tasks
	public List<Blog> getAllTask(){
		return taskList;
	}
	// Returns task  by id
	public Blog findByTaskId(Long id) {
		return getAllTask()
				.stream()
				.filter(task -> task.getNo().equals(id))
				.findFirst()
				.orElse(null);
	}
	// Returns blogs by user's name
	public List<Blog> getByUser(String person) {
		return getAllBlog()
				.stream()
				.filter(blog -> blog.getUser().getName().equalsIgnoreCase(person))
				.toList();		
	}

}
