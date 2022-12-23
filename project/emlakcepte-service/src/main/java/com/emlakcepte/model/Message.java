package com.emlakcepte.model;

public class Message  {
	private Long number;
	private String title;
	private String content;
	private User from;
	private User to;
	

	public Message() {
		super();
	}
	
	public Message(Long number,String title, String content, User from, User to) {
		super();
		this.number = number;
		this.title = title;
		this.content = content;
		this.from = from;
		this.to = to;
	}
	
	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public User getFrom() {
		return from;
	}
	public void setFrom(User from) {
		this.from = from;
	}
	public User getTo() {
		return to;
	}
	public void setTo(User to) {
		this.to = to;
	}

	@Override
	public String toString() {
		return "Message [number=" + number + ", title=" + title + ", content=" + content + ", from=" + from + ", to="
				+ to + "]";
	}

}
