package com.emlakcepte.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.emlakcepte.model.User;

@Repository
public class UserRepository {
	
	// All users are kept here
	private static List<User> userList = new ArrayList<>();
		
	// Adds the new User to the list
	public User createUser(User user) {
		user.setCreateDate(LocalDateTime.now());
		user.setFavoriteRealtyList(Collections.emptyList());
		user.setMessages(Collections.emptyList());
		user.setRealtyList(Collections.emptyList());
		userList.add(user);
		return user;
	}
	// Returns all users
	public List<User> findAllUsers() {	
		return userList;
	}
	// Updates user password and returns it
	public User updateUser(User user, User updateInfo) {
		user.setPassword(updateInfo.getPassword());
		return user;
	}
	// Returns user for given email
	public User getByEmail(String email) {
		return userList
				.stream()
				.filter(user-> user.getMail().equals(email))
				.findFirst()
				.orElse(null);
	}
	// Deletes user for given email
	public User deleteByEmail(User user) {
		userList.remove(user);
		return user;
	}
	// Returns user by name
	public User getByName(String name) {
		return findAllUsers()
		.stream()
		.filter(user-> user.getName().equals(name))
		.findFirst()
		.orElse(null);
	}

}
