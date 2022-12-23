package com.medium.model;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;


public class User  {

	private String name;
	private String mail;
	private String password;
	@JsonIgnore
	private List<Blog> blogList;
	@JsonIgnore
	private List<Tag> tagList;
	@JsonIgnore
	private List<User> followList;
	private LocalDateTime createDate;
	
	public User() {
	}

	public User(String name, String mail, String password) {
		this.name = name;
		this.mail = mail;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Blog> getBlogList() {
		return blogList;
	}

	public void setBlogList(List<Blog> blogList) {
		this.blogList = blogList;
	}

	public List<Tag> getTagList() {
		return tagList;
	}

	public void setTagList(List<Tag> tagList) {
		this.tagList = tagList;
	}

	public List<User> getFollowList() {
		return followList;
	}

	public void setFollowerList(List<User> followList) {
		this.followList = followList;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", mail=" + mail + "]";
	}

	
	
	

}
