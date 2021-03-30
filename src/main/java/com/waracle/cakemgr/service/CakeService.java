package com.waracle.cakemgr.service;

import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.waracle.cakemgr.config.PrepareData;
import com.waracle.cakemgr.model.CakeEntity;
import com.waracle.cakemgr.repository.CakeRepository;

@Service
public class CakeService {
	
	@Autowired
	PrepareData prepareData;
			
	@Autowired
	CakeRepository cakeRepository;
	
	public List<CakeEntity> findAll(){

		return cakeRepository.findAll();
	}

	public CakeEntity add(CakeEntity cakeEntity) {
		
		return cakeRepository.save(cakeEntity);
	}
	
	@PostConstruct
	private void postConstruct() throws JsonMappingException, JsonProcessingException {
        		
		List<CakeEntity> listCakeEntities = prepareData.getListCakeEntities();
		for (CakeEntity cakeEntity : listCakeEntities) {
			cakeRepository.save(cakeEntity);			
		}
    }

}
