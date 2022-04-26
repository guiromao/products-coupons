package co.guiromao.spring.couponservice;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.options;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CouponServiceApplicationTests {

	@Autowired
	MockMvc mvc;

	@Test
	void contextLoads() {
	}

	@Test
	void getCouponWithoutAuth_ShouldReturnUnauthorized() throws Exception {
		mvc.perform(get("/v1/coupons-api/coupons/code-001")).andExpect(status().isUnauthorized());
	}

	@Test
	@WithMockUser(roles = {"USER"})
	void getCouponWithAuth_ShouldReturnSuccess() throws Exception {
		mvc.perform(get("/v1/coupons-api/coupons/code-001")).andExpect(status().isOk())
				.andExpect(content()
						.string("{\"id\":1,\"code\":\"code-001\",\"discount\":40.500,\"expDate\":\"30/11/2022\"}"));
	}

	@Test
	@WithMockUser(roles = {"ADMIN"})
	void createCouponWithoutCsrf_ShouldReturnForbidden() throws Exception {
		mvc.perform(post("/v1/coupons-api/coupons/").content(anyString()))
				.andExpect(status().isForbidden());
	}

	@Test
	@WithMockUser(roles = {"ADMIN"})
	void createCouponWithCsrf_ShouldReturnSuccess() throws Exception {
		mvc.perform(post("/v1/coupons-api/coupons/")
				.content("{\"code\":\"code-csrf\",\"discount\":40.500,\"expDate\":\"30/11/2022\"}")
						.contentType(MediaType.APPLICATION_JSON)
						.with(csrf().asHeader()))
				.andExpect(status().isOk());
	}

	@Test
	@WithMockUser(roles = {"USER"})
	void createCouponWithCsrfAndNotAllowedUser_ShouldReturnForbidden() throws Exception {
		mvc.perform(post("/v1/coupons-api/coupons/")
						.content("{\"code\":\"code-csrf2\",\"discount\":40.500,\"expDate\":\"30/11/2022\"}")
						.contentType(MediaType.APPLICATION_JSON)
						.with(csrf().asHeader()))
				.andExpect(status().isForbidden());
	}

	@Test
	void testCors() throws Exception {
		mvc.perform(options("/v1/coupons-api/coupons/").header("Access-Control-Request-Method", "POST")
				.header("Origin", "https://guiromao.pt"))
				.andExpect(status().isOk())
				.andExpect(header().exists("Access-Control-Allow-Origin"))
				.andExpect(header().string("Access-Control-Allow-Origin", "*"))
				.andExpect(header().exists("Access-Control-Allow-Methods"))
				.andExpect(header().string("Access-Control-Allow-Methods", "POST"));

	}

	@Test
	@WithUserDetails(value = "gromao@mail.com")
	void getWithValidUser_ShouldReturnSuccess() throws Exception {
		mvc.perform(get("/v1/coupons-api/coupons/code-001"))
				.andExpect(status().isOk());
	}

}
