package org.carlosmorenoin.microsexample.api.user;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.carlosmorenoin.microsexample.core.User;
import org.carlosmorenoin.microsexample.core.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(UserAPI.class)
public class UserAPITest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(),
			Charset.forName("utf8"));

	@MockBean
	private UserService userService;

	@Test
	public void addUserNull() throws Exception {
		when(userService.addUser(null)).thenThrow(IllegalArgumentException.class);
		mockMvc.perform(post("/users")).andExpect(status().isBadRequest());
	}

	@Test
	public void addUser() throws Exception {
		User user = new User("Test", "User");
		User userResult = new User("Test", "User");
		userResult.setId("1");
		when(userService.addUser(eq(user))).thenReturn(userResult);

		mockMvc.perform(post("/users")
				.content(objectMapper.writeValueAsString(new User("Test", "User")))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());
	}

	@Test public void retrieveUser() throws Exception {
	}

	@Test public void retrieveUsersEmpty() throws Exception {
		when(userService.retrieveUsers()).thenReturn(Collections.emptyList());
		mockMvc.perform(get("/users")).andExpect(status().isOk())
				.andExpect(content().contentType(contentType))
				.andExpect(jsonPath("$", hasSize(0)));
	}

	@Test public void retrieveUsers() throws Exception {
		final List<User> usersList = new ArrayList<>();
		usersList.add(new User("Test", "User"));
		when(userService.retrieveUsers()).thenReturn(usersList);

		mockMvc.perform(get("/users")).andExpect(status().isOk())
				.andExpect(content().contentType(contentType))
				.andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].firstName", is("Test")))
				.andExpect(jsonPath("$[0].lastName", is("User")));
	}


}

