package com.emlakcepte.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emlakcepte.client.BannerServiceClient;
import com.emlakcepte.client.BannerServiceRestTemplateClient;
import com.emlakcepte.client.model.Banner;
import com.emlakcepte.model.Advert;
import com.emlakcepte.model.Realty;
import com.emlakcepte.model.User;
import com.emlakcepte.model.enums.RealtyKind;
import com.emlakcepte.model.enums.UserType;
import com.emlakcepte.repository.RealtyRepository;

@Service
public class RealtyService {

	@Autowired
	private RealtyRepository realtyRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private BannerServiceClient bannerServiceClient;
	
	// BannserService client
	@Autowired
	private BannerServiceRestTemplateClient bannerServiceRestTemplateClient;

	/* If userType is Indıvıdual, user can create maximum 3 housing
	 *  If userType is Corparete, user create limitless realty
	 * First, it queries whether the user is registered in the system,
	 *  then it queries whether this realty is unique.
	 */
	public Realty create(Realty realty) {
		User user = userService.getByName(realty.getUser().getName());
		Realty matchingRealty = getRealtyById(realty.getNo());
		if (user != null && matchingRealty == null && realty.getTitle() != null) {
			return getRealty(user.getType(), user, realty);
		}
		System.out.println("Try again!!");
		return null;
	}
	// Checks user, usertype if fits conditions create a new realty
	public Realty getRealty(UserType type, User user, Realty realty) {
		switch (type) {
		case INDIVIDUAL:
			if (controlIndıvıdualUserRealty(user, realty)) {
				System.out.println(realty + " created by " + user + realty.getType());
				return createHelper(user, realty);
			}
			System.out.println(realty.getUser().getName() + " has aldready 3 of them");
			return null;
		case CORPARETE:
			System.out.println(realty + " created by " + user + realty.getType());
			return createHelper(user, realty);
		default:
			System.out.println("Try again!!");
			return null;
		}

	}

	// Check individual user realty
	public boolean controlIndıvıdualUserRealty(User user, Realty realty) {
		long count = realtyRepository.controlIndıvıdualRealty(user, realty);
		if (count < 3 && realty.getType().equals(RealtyKind.HOUSING)) {
			return true;
		}
		return false;
	}
	// It helps create realty steps, 
	// a free banner is sent to the banner service when an banner is created
	public Realty createHelper(User user, Realty realty) {
		String address = String.join(" ", realty.getProvince(), realty.getDistrict());
		Banner banner = new Banner(String.valueOf(realty.getNo()), 1, "123123123", "",address);
		bannerServiceRestTemplateClient.save(banner);
		//bannerServiceClient.create(banner);
		realty.setUser(user);
		realty.setPublishedDate(LocalDateTime.now());
		addList(user, realty);
		return realtyRepository.createRealty(realty);
	}
	// Adds new realty to user's realty list
	public void addList(User user, Realty realty) {
		List<Realty> list = user.getRealtyList();
		List<Realty> realties = new ArrayList<>(list);
		realties.add(realty);
		user.setRealtyList(realties);
	}
	// Returns all realties
	public List<Realty> getAll() {
		return realtyRepository.findAll();
	}
	// Returns realty by realty number
	public Realty getRealtyById(Long no) {
		return realtyRepository.findById(no);
	}
	// Returns number of realty by province
	public long getNumberOfProvinceRealty(String province) {
		return realtyRepository.findNumberOfProvinceRealty(province);
	}
	// Returns number of housing on sale
	public long getNumberOfHousingOnSale(String province) {
		return realtyRepository.getNumberOfHousingOnSale(province);
	}
	// Returns realty list for cities
	public List<Realty> getAllProvinceRealty(String province) {
		return realtyRepository.getByAllProvince(province);	 
	}
	// Returns all listings by city and district
	public List<Realty> getAllByProvinceAndDistrict(String province, String district) {
		return realtyRepository.getAllByProvinceAndDistrict(province, district);
	}
	// Returns all realties by username
	public List<Realty> getRealtyByUser(String user) {
		return realtyRepository.getByUser(user);
	}
	// Returns all city adverts
	public List<Advert> getAllAdverts() {
		return realtyRepository.getAllAdverts();
	}
    // If the number of realty in a city is more than 10,
	// the city showcase is created and return it.
	public Advert getAdvert(String province) {
		if (checkProvinceStatus(province)) {
			List<Realty> realty = getAllProvinceRealty(province);
			return addAdvertList(province, realty);
		}
		return null;
	}
	// Checks number of province by cities
	public boolean checkProvinceStatus(String province) {
		if (getNumberOfProvinceRealty(province) >= 10)
			return true;
		return false;
	}
	// Creates a city advert
	public Advert addAdvertList(String province, List<Realty> realty) {
		Advert advert = new Advert();
		advert.setName(province);
		advert.setAdvertList(Collections.emptyList());
		List<Realty> list = realty;
		List<Realty> adverts = new ArrayList<>(list);
		advert.setAdvertList(adverts);
		return realtyRepository.createAdvert(advert);
	}
}
