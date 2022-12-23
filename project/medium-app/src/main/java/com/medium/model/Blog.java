package com.medium.model;

import java.time.LocalDateTime;



public class Blog {
	private Long no;
	private String title;
	private String content;
	private String image;
	private LocalDateTime publishedDate;
	private User user;
	private Status status;
	private Tag tag;
	
	public Blog() {
	}

	public Blog(Long no, String title, String content, String image, User user, Tag tag) {
		this.no = no;
		this.title = title;
		this.content = content;
		this.image = image;
		this.user = user;
		this.tag = tag;
	}

	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public LocalDateTime getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(LocalDateTime publishedDate) {
		this.publishedDate = publishedDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Tag getTag() {
		return tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}

	@Override
	public String toString() {
		return "Blog [no=" + no + ", title=" + title + ", content=" + content + ", image=" + image + ", publishedDate="
				+ publishedDate + ", user=" + user.getName() + ", status=" + status + ", tag=" + tag + "]";
	}
	
	
	

}
