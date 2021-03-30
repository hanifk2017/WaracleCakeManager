package com.waracle.cakemgr.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.waracle.cakemgr.model.CakeEntity;

@Configuration
public class PrepareData {

	@Value("${cake.dataUrl}")
	String dataUrl;

	List<CakeEntity> listCakeEntities;

	public List<CakeEntity> getListCakeEntities() throws JsonMappingException, JsonProcessingException {

		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<String> response = restTemplate.getForEntity(dataUrl, String.class);

		ObjectMapper objectMapper = new ObjectMapper();

		listCakeEntities = objectMapper.readValue(response.getBody(), new TypeReference<List<CakeEntity>>() {
		});

		return listCakeEntities;
	}

}
