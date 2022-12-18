package com.emlakcepte.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emlakcepte.model.Message;
import com.emlakcepte.service.MessageService;

@RestController
@RequestMapping(value = "/messages")
public class MessageController {
	
	@Autowired
	private MessageService messageService;
	
	// Returns all messages
	@GetMapping
	public ResponseEntity<List<Message>> getAll(){
		List<Message> messages = messageService.findAll();
		if(messages != null)
			return new ResponseEntity<>(messages, HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	// Returns added message
	@PostMapping
	public ResponseEntity<Message>  create(@RequestBody Message newMessage) {
		Message message = messageService.create(newMessage);
		if(message != null) 
			return new ResponseEntity<>(message, HttpStatus.CREATED); 
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST); 
	}
	// Gets the message on the requested id
	@GetMapping(value = "/{id}")
	public ResponseEntity<Message> findById(@PathVariable Long id) { 
		Message message = messageService.findById(id);
		if(message != null)
		    return new ResponseEntity<>(message, HttpStatus.OK);
	    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	// Returns messages of user
	@GetMapping(value = "/name/{name}")
	public ResponseEntity<List<Message>> findByName(@PathVariable String name) {
		List <Message> messages = messageService.findByName(name);
		if(messages != null)
		    return new ResponseEntity<> (messages, HttpStatus.OK);
	    return new ResponseEntity<> (HttpStatus.NOT_FOUND);
	}
	// Updates the message on the requested id
	@PutMapping(value = "/{id}")
	public ResponseEntity<Message> update(@PathVariable Long id, @RequestBody Message newMessage){
		Message message = messageService.update(id, newMessage);
		if(message != null)
		    return new ResponseEntity<>(message, HttpStatus.OK);
	    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	// Deletes user messages
	@DeleteMapping(value = "/delete/{name}")
	public ResponseEntity<String> delete(@PathVariable String name) {
		if(messageService.deleteAll(name))
			return new ResponseEntity<>("Message deleted!!", HttpStatus.OK);
	    return new ResponseEntity<>("Message could not be deleted",HttpStatus.NOT_FOUND);
	}
	
	

}
