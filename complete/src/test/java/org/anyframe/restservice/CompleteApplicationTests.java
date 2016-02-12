package org.anyframe.restservice;

import org.anyframe.restservice.domain.User;
import org.anyframe.restservice.repository.jpa.RegisteredUserJpaRepository;
import org.anyframe.restservice.util.TestUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.Charset;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RestServiceApplication.class)
@WebAppConfiguration
public class CompleteApplicationTests {

	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(),
			Charset.forName("utf8"));

	private MockMvc mockMvc;

	private User user = new User();

	@Autowired
	private RegisteredUserJpaRepository registeredUserJpaRepository;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setup() throws Exception {
		this.mockMvc = webAppContextSetup(webApplicationContext).build();

		this.registeredUserJpaRepository.deleteAllInBatch();

		this.user = registeredUserJpaRepository.save(new User("1234", "test", "test@anyframecloud.org", "kim", "test"));
	}

	@Test
	public void registerUser() throws Exception {
		String userJson = TestUtils.asJsonString(new User(
				"", "test2", "test2@anyframecloud.org", "kim", "test2"));
		this.mockMvc.perform(post("/user/")
				.contentType(contentType)
				.content(userJson))
				.andExpect(status().isCreated());
	}

	@Test
	public void getUserById() throws Exception {
		mockMvc.perform(get("/user/"
				+ this.user.getId()))
				.andExpect(status().isOk())
				.andExpect(content().contentType(contentType))
				.andExpect(jsonPath("$.loginName", is(this.user.getLoginName())))
				.andExpect(jsonPath("$.emailAddress", is(this.user.getEmailAddress())))
				.andExpect(jsonPath("$.firstName", is(this.user.getFirstName())))
				.andExpect(jsonPath("$.lastName", is(this.user.getLastName())));
	}

	@Test
	public void modifyUser() throws Exception {
		String userJson = TestUtils.asJsonString(new User(
				this.user.getId(), "modify", "modify@anyframecloud.org", "kim", "modify"));
		this.mockMvc.perform(put("/user/" + this.user.getId())
				.contentType(contentType)
				.content(userJson))
				.andExpect(status().isNoContent());
	}

	@Test
	public void deleteUser() throws Exception {
		this.mockMvc.perform(delete("/user/" + this.user.getId()))
				.andExpect(status().isResetContent());
	}

}
