package com.medium.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medium.model.Tag;
import com.medium.model.User;
import com.medium.repository.TagRepository;

@Service
public class TagService {
	// Singleton pattern
	@Autowired
	private TagRepository tagRepository;

	@Autowired 
	private UserService userService;

	// First check if the tag is unique then creates a new tag
	public Tag createTag(Tag tag) {
		Tag matchingObject = tagRepository.searchTag(tag);
		if(matchingObject != null) {
			return null;
		}
		System.out.println("tags: " + tag);
		return tagRepository.create(tag);
	}
	// Returns tag list
	public List<Tag> getAllTag(){
		System.out.println("tags: " + tagRepository.getAll());
		return tagRepository.getAll();
	}
	// Returns tag by tag name
	public Tag getByName(String name) {
		return tagRepository.getByName(name);
	}
	// First checks user and tag then add the new tag to the user's tag list
	public List<Tag> followTag(User person, String tagName) {
		User user = userService.getByName(person.getName());
		Tag tag = getByName(tagName);
		if(user != null && tag != null  && !user.getTagList().contains(tag)) {
			return followTag(user, tag);
		}
		return null;
	}
	// Invoke addTag method then return user tag list
	public List<Tag> followTag(User user, Tag tag) {
		addTag(user,tag);
		return user.getTagList();
		
	}
	// Adds a new tag to user tag list
	public void addTag(User user, Tag tag) {
		List<Tag> list = user.getTagList();
		List<Tag> tags = new ArrayList<>(list);
		tags.add(tag);
		user.setTagList(tags);
	}
	// Returns following tags by user
	public List<User> getFollowingTag(String tagName){
		Tag tag = getByName(tagName);
		return userService.getAllUser()
				.stream()
				.filter(user -> user.getTagList()
						.contains(tag)).toList();
	}
	// Returns user's tag list
	public List<Tag> getUserTags(String person) {
		User user = userService.getByName(person);
		if (user != null)
			return user.getTagList();
		return null;
	}

}
