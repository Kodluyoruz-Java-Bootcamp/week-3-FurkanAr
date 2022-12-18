package com.emlakcepte.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.emlakcepte.model.Advert;
import com.emlakcepte.model.Realty;
import com.emlakcepte.model.User;
import com.emlakcepte.model.enums.Category;
import com.emlakcepte.model.enums.RealtyKind;
import com.emlakcepte.model.enums.UserType;


@Repository
public class RealtyRepository {

	// List of realty
	private static List<Realty> realtyList = new ArrayList<>();
	private static List<Advert> advertList = new ArrayList<>();

	// Adds the new realty to the list
	public Realty createRealty(Realty realty) {
		realtyList.add(realty);
		return realty;
	}
	// Returns all realtyes
	public List<Realty> findAll(){
		return realtyList;
	}
	// Returns realtyes by user
	public List<Realty> getByUser(String name){
		return findAll()
				.stream()
				.filter(realty -> realty.getUser().getName().equalsIgnoreCase(name))
				.toList();		
	}
	// Checks individual user realtyes
	public Long controlIndıvıdualRealty(User user, Realty realty) {
		return getByUser(user.getName())
				.stream()
				.filter(c -> c.getUser().getType().equals(UserType.INDIVIDUAL))
				.filter(c -> c.getType().equals(realty.getType()))
				.count();
	}
	// Returns realty by realty id 
	public Realty findById(Long id) {
		return findAll()
				.stream()
				.filter(number -> number.getNo().equals(id))
				.findFirst()
				.orElse(null);
	}
	// Returns realtyes by city
	public List<Realty> getByAllProvince(String province) {
		return findAll()
				.stream()
				.filter(realty -> realty.getProvince().equals(province))
				.toList();		
	}
	// Returns number of realtyes by city
	public Long findNumberOfProvinceRealty(String province) {
		return  getByAllProvince(province)
				.stream()
				.count();
	}
	// Returns the number of housing for sale by city
	public long getNumberOfHousingOnSale(String province) {
		return findAll()
				.stream()
				.filter(realty -> realty.getProvince().equals(province))
				.filter(realty -> realty.getCategory().equals(Category.SALE))
				.filter(realty -> realty.getType().equals(RealtyKind.HOUSING))
				.count();
	}
	// Returns realtyes by city and district 
	public List<Realty> getAllByProvinceAndDistrict(String province, String district) {
		return findAll()
				.stream()
				.filter(realty -> realty.getProvince().equalsIgnoreCase(province))
				.filter(realty -> realty.getDistrict().equalsIgnoreCase(district))
				.toList();
	}
	
	// Adds the new advert to the list
	public Advert createAdvert(Advert advert) {
		advertList.add(advert);
		return advert;
	}
	
	// Returns all city adverts
	public List<Advert> getAllAdverts(){
		return advertList;
	}


}
