package com.apica.UserMngService.controller;

import com.apica.UserMngService.service.UsrMngrService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(UsrMngrController.class)
public class UsrMngrControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UsrMngrService usrMngrService;

	@Test
	public void updateUser() throws Exception {
		// this.mockMvc.perform(put("/users/{id}", "abc").content("abc").contentType(MediaType.APPLICATION_JSON_VALUE)).
		//   andExpect(status().isOk()).
		//   andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE)).
		//   andExpect(jsonPath("$.id").value("<value>")).
		//   andExpect(jsonPath("$.username").value("<value>")).
		//   andExpect(jsonPath("$.email").value("<value>")).
		//   andExpect(jsonPath("$.address").value("<value>"));
	}

	@Test
	public void createUser() throws Exception {
		// this.mockMvc.perform(post("/users").content("abc").contentType(MediaType.APPLICATION_JSON_VALUE))
		// 	.andExpect(status().isOk())
		// 	.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
		// 	.andExpect(jsonPath("$.id").value("<value>"))
		// 	.andExpect(jsonPath("$.username").value("<value>"))
		// 	.andExpect(jsonPath("$.email").value("<value>"))
		// 	.andExpect(jsonPath("$.address").value("<value>"));
	}
}
