package com.emlakcepte.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import com.emlakcepte.client.model.Banner;

@Controller
public class BannerServiceRestTemplateClient {
	// Banner service url
	private static final String webUrl = "http://localhost:8081/banners";
	
	@Autowired 
	private RestTemplate restTemplate;
	
	// Get all banners from banner service and return it 
	@GetMapping
	public ResponseEntity<List<Banner>> getBanners(){
		ResponseEntity<List> result = restTemplate.getForEntity(webUrl, List.class);
		List<Banner> responseBody = result.getBody();
		return ResponseEntity.ok(responseBody);
	}
	
	// Send banner to banner service
	@PostMapping
	public ResponseEntity<Banner> save(@RequestBody Banner banner){
		ResponseEntity<Banner> result = restTemplate.postForEntity(webUrl, banner, Banner.class);
		Banner responseBody = result.getBody();
		return ResponseEntity.ok(responseBody);
	}

	
	
	
	
}
