package com.emlakcepte.model;

import java.util.List;

public class Advert {
	private String name;
	private List<Realty> advertList;
	
	public Advert() {
	
	}

	public Advert(String name,List<Realty> advertList) {
		super();
		this.name = name;
		this.advertList = advertList;
	}

	public List<Realty> getAdvertList() {
		return advertList;
	}

	public void setAdvertList(List<Realty> advertList) {
		this.advertList = advertList;
	}
	
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Advert [name=" + name + ", advertList=" + advertList + "]";
	}


	
	
}
