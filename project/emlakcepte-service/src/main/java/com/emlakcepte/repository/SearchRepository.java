package com.emlakcepte.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.emlakcepte.model.Search;

@Repository
public class SearchRepository {
	// All searches
	private static List<Search> searchList = new ArrayList<>();
	
	// Adds the new search to the list
	public Search saveSearch(Search search) {
		searchList.add(search);
		return search;
	}
	// Returns all searches
	public List<Search> findAll(){
		return searchList;
	}
	// Deletes given searches
	public void delete(List<Search> list){
		findAll().removeAll(list);
	}
	// Returns messages for given user
	public List<Search> findByName(String name) {
		return findAll()
				.stream()
				.filter(n -> n.getUser().getName().equalsIgnoreCase(name))
				.toList();
	}
}
