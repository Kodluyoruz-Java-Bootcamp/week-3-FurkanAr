package com.medium.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.medium.model.User;

@Repository
public class UserRepository  {
	// List of all user
	private  List<User> userList = new ArrayList<>();

	// Adds the new user to the list
	public User createUser(User user) {
		user.setCreateDate(LocalDateTime.now());
		user.setBlogList(Collections.emptyList());
		user.setFollowerList(Collections.emptyList());
		user.setTagList(Collections.emptyList());
		userList.add(user);
		return user;
	}
	// Returns users
	public List<User> getAllUsers(){
		System.out.println("users: " + userList);
		return userList;
	}
	// Returns user by username
	public User getByName(String name) {
		return getAllUsers()
				.stream()
				.filter(user-> user.getName().equals(name))
				.findFirst()
				.orElse(null);
	}
	// Updates user's password
	public User updateUser(User user, User updateInfo) {
		user.setPassword(updateInfo.getPassword());
		return user;
	}
}
