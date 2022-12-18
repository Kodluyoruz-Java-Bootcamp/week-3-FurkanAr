package com.emlakcepte.model;

public class Search {
	
	String placeHolder;
	User user;
	
	public Search() {
	}
	
	public Search(String placeHolder, User user) {
		super();
		this.placeHolder = placeHolder;
		this.user = user;
	}
	
	public String getPlaceHolder() {
		return placeHolder;
	}

	public void setPlaceHolder(String placeHolder) {
		this.placeHolder = placeHolder;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Search [placeHolder=" + placeHolder + ", user=" + user + "]";
	}


}
