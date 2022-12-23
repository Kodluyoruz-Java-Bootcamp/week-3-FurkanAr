package com.emlakcepte.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emlakcepte.model.Advert;
import com.emlakcepte.model.Realty;
import com.emlakcepte.service.RealtyService;

@RestController
@RequestMapping(value = "/realtyes")
public class RealtyController {
	
	@Autowired
	private RealtyService realtyService;
	
	// Returns all realtyes
	@GetMapping
	public ResponseEntity<List<Realty>> getAll() {
		List<Realty> realtyes = realtyService.getAll();
		if(realtyes != null)
			return new ResponseEntity<>(realtyes, HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	// Create new realty and return added realty
	@PostMapping
	public ResponseEntity<Realty> create(@RequestBody Realty newRealty) {
		Realty realty = realtyService.create(newRealty);
		if(realty !=  null )
			return new ResponseEntity<>(realty, HttpStatus.CREATED);
		return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
	}
	// Returns the number of realtyes in the city
	@GetMapping(value = "/{province}")
	public ResponseEntity<Long> getNumberOfProvinceRealty(@PathVariable String province) {
		long number = realtyService.getNumberOfProvinceRealty(province);
		return new ResponseEntity<>(number, HttpStatus.OK);
	}
	// Returns realtyes in the city
	@GetMapping(value = "/city/{province}")
	public ResponseEntity<List<Realty>> getAllProvinceRealty(@PathVariable String province) {
		List<Realty> realtyes = realtyService.getAllProvinceRealty(province);
		if (realtyes != null)
			return new ResponseEntity<>(realtyes, HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	// Returns the number of housing for sale in the city
	@GetMapping(value = "/Housing/{province}")
	public ResponseEntity<Long>  findNumberOfHousingOnSale(@PathVariable String province) { 
		long number = realtyService.getNumberOfHousingOnSale(province);
		return new ResponseEntity<>(number, HttpStatus.OK);
	}
	// Returns realtyes in cities and district
	@GetMapping(value = "/{province}/{district}")
	public ResponseEntity<List<Realty>> findByProvinceAndDistrict(@PathVariable String province, @PathVariable  String district) { 
		List<Realty> realtyes = realtyService.getAllByProvinceAndDistrict(province, district);
		if(realtyes != null)
			return new ResponseEntity<>(realtyes, HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	// Returns user realtyes
	@GetMapping(value = "/my/{name}")
	public  ResponseEntity<List<Realty>> findByUser(@PathVariable String name) { 
		List<Realty> realtyes = realtyService.getRealtyByUser(name);
		if (realtyes != null)
			return new ResponseEntity<>(realtyes, HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	// Returns city advert realtyes
	@GetMapping(value = "/advert/{province}")
	public ResponseEntity<Advert> createAdvert(@PathVariable String province) {
		Advert advert = realtyService.getAdvert(province);
		if (advert !=  null )
			return new ResponseEntity<>(advert, HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	// Returns adverts 
	@GetMapping(value = "/advert")
	public  ResponseEntity<List<Advert>> getAllAdverts() { 
		List<Advert> adverts = realtyService.getAllAdverts();
		if (adverts !=  null)
			return new ResponseEntity<>(adverts, HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
