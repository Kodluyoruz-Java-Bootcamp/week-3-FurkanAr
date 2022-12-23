package com.emlakcepte.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.emlakcepte.client.model.Banner;


@FeignClient(value = "emlakcepte-banner", url = "http://localhost:8081")
public interface BannerServiceClient {

	@PostMapping(value = "/banners")
	Banner create(@RequestBody Banner banner);

}
	
