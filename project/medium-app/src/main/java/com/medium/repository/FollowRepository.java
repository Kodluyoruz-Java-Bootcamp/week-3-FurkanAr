package com.medium.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.medium.model.User;

@Repository
public class FollowRepository {
	
	// Returns user's following
	public List<User> follow(User userFrom, User userTo) {
		System.out.println("follow repo:" + userFrom + userTo);
		return getFollow(userFrom);
	}

	// Returns users followers
	public List<User> getFollow(User user){
		return user.getFollowList();
	}
	

}
