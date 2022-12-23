package com.emlakcepte.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.stereotype.Repository;

import com.emlakcepte.model.Message;

@Repository
public class MessageRepository {
	
	// All messages are kept here
	private static List<Message> messageList = new ArrayList<>();
	
	// Adds the new message to the list
	public Message save(Message message) {
		messageList.add(message);
		return message;
	}
	// Returns all messages
	public List<Message> findAllMessages(){
		return messageList;
	}
	// Returns message by message id 
	public Message findById(Long id) {
		return  findAllMessages()
		.stream()
		.filter(num -> num.getNumber().equals(id))
		.findFirst()
		.orElse(null);
	}
	// Returns all messages including the user
	public List<Message> findByName(String name) {
		List<Message> messagesTo = findAllMessages()
				.stream()
				.filter(n -> n.getFrom().getName().equalsIgnoreCase(name))
				.toList();
		
		List<Message> messagesFrom = findAllMessages()
				.stream()
				.filter(n -> n.getTo().getName().equalsIgnoreCase(name))
				.toList();
		
		List<Message> messageList = Stream.concat(messagesTo.stream(), 
										messagesFrom.stream()).toList();
		return messageList;
	}
	// Deletes given messages
	public void deleteAll(List<Message> list){
		findAllMessages().removeAll(list);
	}
	// Updates specific message
	public Message update(Long id, Message newMessage) {
		Message oldMessage = findById(id);	
		oldMessage.setTitle(newMessage.getTitle());
		oldMessage.setContent(newMessage.getTitle());
		return oldMessage;
	}
}
