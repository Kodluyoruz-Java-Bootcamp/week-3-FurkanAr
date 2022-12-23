package com.emlakcepte.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emlakcepte.model.Search;
import com.emlakcepte.service.SearchService;

@RestController
@RequestMapping(value="/search")
public class SearchController {

	@Autowired
	private SearchService searchService;
	
	// Returns all searches
	@GetMapping
	public ResponseEntity<List<Search>> getAll(){
		List<Search> searches = searchService.getAll();
		if(!searches.isEmpty())
			return new ResponseEntity<>(searches, HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	// Returns added search
	@PostMapping
	public ResponseEntity<Search> create(@RequestBody Search newSearch) {
		Search search = searchService.createSearch(newSearch);
		if(search != null)
			return new ResponseEntity<>(search, HttpStatus.CREATED);
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	// Deletes user searches
	@DeleteMapping(value = "/delete/{name}")
	public ResponseEntity<String> delete(@PathVariable String name){
		if(searchService.deleteAll(name))
			return new ResponseEntity<>("Deleted", HttpStatus.OK);
		return new ResponseEntity<>("Error!! Check username", HttpStatus.NOT_FOUND);
	}
	// Returns user searches
	@GetMapping(value = "/{name}")
	public ResponseEntity<List<Search>> getByName(@PathVariable String name){
		List<Search> search = searchService.findByName(name);
		if(!search.isEmpty())
			return new ResponseEntity<> (search, HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
