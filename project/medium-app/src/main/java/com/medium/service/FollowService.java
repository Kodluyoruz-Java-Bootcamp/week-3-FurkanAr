package com.medium.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medium.model.User;
import com.medium.repository.FollowRepository;
@Service
public class FollowService {
	// Singleton pattern
	@Autowired
	private FollowRepository followerRepository;
	@Autowired
	private UserService userService;

	// First it checks the user information then creates the following 
	public List<User> follow(User userFrom, String userTo) {
		User userFollow = userService.getByName(userFrom.getName());
		User userTaken = userService.getByName(userTo);
		System.out.println("follow metod:" + userFollow + userTaken);
		if(userFollow != null && userTaken != null && userFollow != userTaken &&
				!userFollow.getFollowList().contains(userTaken)) {
			System.out.println("follow metod if:" + userFollow + userTaken);
			addList(userFollow, userTaken);
			return followerRepository.follow(userFollow, userTaken);
		}
		return null;
	}
	// Add follower to user follow list
	public void addList(User userFrom, User userTo) {
		List<User> list = userFrom.getFollowList();
        List<User> users = new ArrayList<>(list);
        users.add(userTo);
		userFrom.setFollowerList(users);
	}
	// Returns user's follows list
	public List<User> getFollowed(String user){
		User userFollow = userService.getByName(user);
		return followerRepository.getFollow(userFollow);
	}
	// Return user's follower list
	public List<User> getFollowers(String user){
		User userFollow = userService.getByName(user);
		if(userFollow != null)
			return userService.getAllUser()
					.stream()
					.filter(person -> person.getFollowList()
							.contains(userFollow))
					.toList();
		return null;
	}
	
}
