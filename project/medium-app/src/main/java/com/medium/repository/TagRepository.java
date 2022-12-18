package com.medium.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.medium.model.Tag;

@Repository
public class TagRepository {
	// List of all tags
	private static List<Tag> tagList = new ArrayList<>();

	// Adds the new tag to the list
	public Tag create(Tag tag) {
		System.out.println("tag: " + tag);
		tagList.add(tag);	
		return tag;
	}
	// Returns all tags
	public List<Tag> getAll(){
		return tagList;
	}
	// Returns tag's by tag title
	public Tag getByName(String name) {
		return getAll()
				.stream()
				.filter(tag-> tag.getTitle().equals(name))
				.findFirst()
				.orElse(null);
	}
	// Returns tag
	public Tag searchTag(Tag tag) {
		return getAll()
				.stream()
				.filter(t-> t.getTitle().equalsIgnoreCase(tag.getTitle()))
				.findAny()
				.orElse(null);
	}
}
