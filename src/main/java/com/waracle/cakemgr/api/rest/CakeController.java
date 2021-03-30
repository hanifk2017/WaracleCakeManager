package com.waracle.cakemgr.api.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.waracle.cakemgr.model.CakeEntity;
import com.waracle.cakemgr.service.CakeService;

@RestController

public class CakeController {

	@Autowired
	private CakeService cakeService;

	@GetMapping(value = "/cakes", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<CakeEntity> getAllUsers() {
		return cakeService.findAll();
	}

	@PostMapping(value = "/cakes", consumes = MediaType.APPLICATION_JSON_VALUE)
	public CakeEntity createUser(@Validated @RequestBody CakeEntity cakeEntity) {
		return cakeService.add(cakeEntity);
	}
}
