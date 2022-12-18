package com.medium.model;

public class Follow {

	private User userTo;

	public Follow() {

	}
	
	public Follow(User userTo) {
		this.userTo = userTo;
	}
	
	public User getUserTo() {
		return userTo;
	}
	public void setUserTo(User userTo) {
		this.userTo = userTo;
	}

	@Override
	public String toString() {
		return "user=" + userTo.getName();
	}
	
	

	
	
	
}
