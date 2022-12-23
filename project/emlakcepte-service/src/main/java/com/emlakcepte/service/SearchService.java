package com.emlakcepte.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emlakcepte.model.Search;
import com.emlakcepte.repository.SearchRepository;
import com.emlakcepte.model.User;

@Service
public class SearchService {
	
	@Autowired
	private SearchRepository searchRepository;
	@Autowired
	private UserService userService;
	
	// Checks if the searcher is in the system, 
	//saves if the search object satisfies the conditions
	public Search createSearch(Search search) {
		User user = userService.getByName(search.getUser().getName());
		if(user != null && search.getPlaceHolder() != null) {
			System.out.println("search: " +search);
			search.setUser(user);
			return searchRepository.saveSearch(search);
		}
		return null;
	}
	// Returns all searches
	public List<Search> getAll(){
		System.out.println("search: " +searchRepository.findAll());
		return searchRepository.findAll();
	}
	// Returns user searches
	public List<Search> findByName(String name){
		return searchRepository.findByName(name);

	}
	// Deletes user searches
	public boolean deleteAll(String name) {
		List<Search> list = findByName(name);
		searchRepository.delete(list);
		if (getAll().isEmpty())
			return true;
		return false;
	}
}
