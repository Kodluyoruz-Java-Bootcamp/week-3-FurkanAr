package com.emlakcepte.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.emlakcepte.model.Banner;
import com.emlakcepte.service.BannerService;

@RestController
public class BannerController {

	@Autowired
	private BannerService bannerService;
	
	@PostMapping(value = "/banners")
	public Banner create(@RequestBody Banner banner) {
		bannerService.create(banner);
		return banner;
	}

	@GetMapping(value = "/banners")
	public List<Banner> getAll() {
		return bannerService.getAll();
	}
}
