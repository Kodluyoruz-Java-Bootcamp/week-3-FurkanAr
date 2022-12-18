package com.emlakcepte.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emlakcepte.model.Message;
import com.emlakcepte.model.User;
import com.emlakcepte.repository.MessageRepository;

@Service
public class MessageService {
	
	@Autowired
	private MessageRepository messageRepository;
	
	@Autowired
	private UserService userService;
	
	/*
		first it checks if the message exists, then
		Checks if the messaging users are in the system.
		If there is no problem in the matches, it creates the message.
	 */
	public Message create(Message message) {
		Message matchingMessage = findById(message.getNumber());
		User userFrom = getUser(message.getFrom().getName());
		User userTo = getUser(message.getTo().getName());
		if(matchingMessage == null && userFrom != null && 
				userFrom != userTo && userTo != null && message.getTitle() != null) {
			System.out.println("message: "+ message); 
			message.setFrom(userFrom);
			message.setTo(userTo);
			addUserMessage(userFrom, userTo, message);
			return messageRepository.save(message);
		} 
		return null;
	}
	// Userservice getByname methods returns the user who gave the name
	private User getUser(String name) {
		return userService.getByName(name);
	}
	// Adds new messages to the user's message list
	private void addUserMessage(User userFrom, User userTo, Message message) {
		addMessage(userFrom, message);
		addMessage(userTo, message);
	}
	// It helps addUserMessage
	private void addMessage(User user, Message message) {
		List<Message> userMessage = user.getMessages();
		List<Message> userMessageList = new ArrayList<>(userMessage);
		userMessageList.add(message);
		user.setMessages(userMessageList);
	}
	// Returns all messages
	public List<Message> findAll(){
		return messageRepository.findAllMessages();
	}
	// Return message by given id
	public Message findById(Long id){
		return messageRepository.findById(id);
	}
	// Return user's message given by name
	public List<Message> findByName(String name){
		return messageRepository.findByName(name);
	}
	// Updates message given id
	public Message update(Long id, Message message){
		Message oldMessage = findById(id);
		if (oldMessage != null && oldMessage.getFrom().getName().equals(message.getFrom().getName())) {
			return messageRepository.update(id,message);
		}
		return null;
	}
	// Deletes users' messages
	public boolean deleteAll(String name) {
		User user = getUser(name);
		List<Message> list = user.getMessages();
		if(user != null)
			messageRepository.deleteAll(list);
		if(findAll().isEmpty())
			return true;
		return false;
	}

}
