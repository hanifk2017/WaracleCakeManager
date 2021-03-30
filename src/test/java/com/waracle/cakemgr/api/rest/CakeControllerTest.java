package com.waracle.cakemgr.api.rest;

import static org.hamcrest.CoreMatchers.any;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.waracle.cakemgr.model.CakeEntity;
import com.waracle.cakemgr.service.CakeService;

@WebMvcTest(CakeController.class)
public class CakeControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CakeService service;

	private List<CakeEntity> listCakeEntities = new ArrayList<>();

	@BeforeEach
	public void setUp() {

		CakeEntity cakeEntity = new CakeEntity();

		cakeEntity.setDesc("desc_1");
		cakeEntity.setTitle("title_1");
		cakeEntity.setImage("image_1");

		listCakeEntities.add(cakeEntity);

		cakeEntity = new CakeEntity();

		cakeEntity.setDesc("desc_2");
		cakeEntity.setTitle("title_2");
		cakeEntity.setImage("image_2");

		listCakeEntities.add(cakeEntity);

	}

	@Test
	public void shouldReturnCakesFromService() throws Exception {
		
		when(service.findAll()).thenReturn(listCakeEntities);
		this.mockMvc.perform(get("/cakes"))
        	.andExpect(content().contentType("application/json"))
        	.andExpect(status().isOk())
			.andExpect(jsonPath("$", hasSize(2))).andExpect(jsonPath("$", hasSize(2)))
			.andExpect(jsonPath("$[0].desc", is("desc_1")))
			.andExpect(jsonPath("$[0].title", is("title_1")))
			.andExpect(jsonPath("$[0].image", is("image_1")))
			.andExpect(jsonPath("$[1].desc", is("desc_2")))
			.andReturn();
	}
	
	@Test
	public void shouldInsertCake() throws Exception {
		
		CakeEntity newCakeEntity = listCakeEntities.get(0);
		
		when(service.add(Mockito.any(CakeEntity.class))).thenReturn(newCakeEntity);
		this.mockMvc.perform(post("/cakes")
				  .content(new ObjectMapper().writeValueAsString(newCakeEntity))
				  .contentType("application/json").accept("application/json"))
	              .andExpect(status().isOk())
	              .andExpect(content().contentType("application/json"));
			}
	}
	

