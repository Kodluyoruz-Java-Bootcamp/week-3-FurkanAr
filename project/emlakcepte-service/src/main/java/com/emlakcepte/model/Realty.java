package com.emlakcepte.model;

import java.time.LocalDateTime;

import com.emlakcepte.model.enums.Category;
import com.emlakcepte.model.enums.RealtyKind;
import com.emlakcepte.model.enums.RealtyType;

public class Realty {
	private Long no;
	private String title;
	private LocalDateTime publishedDate;
	private User user;
	private RealtyType status;
	private String province;
	private String district;
	private Category category;
	private RealtyKind type;
	
	public Realty() {
	}
	
	public Realty(Long no, String title, LocalDateTime publishedDate, User user, RealtyType status, Category category, RealtyKind type) {
		super();
		this.no = no;
		this.title = title;
		this.publishedDate = publishedDate;
		this.user = user;
		this.status = status;
		this.category = category;
		this.type = type;
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

	public RealtyType getStatus() {
		return status;
	}

	public void setStatus(RealtyType status) {
		this.status = status;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}
	
	

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}
	

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	

	public RealtyKind getType() {
		return type;
	}

	public void setType(RealtyKind type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Realty [no=" + no + ", title=" + title + ", publishedDate=" + publishedDate + ", user=" + user + ", type=" +type
				+ ", status=" + getStatus() + ", category=" + getCategory() + ", province=" + province + ", district=" + district + "]";
	}


}
