package com.lefu.test.user.web;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.lefu.test.common.WebAppContextSetupTest;

public class CustomerControllerTest extends WebAppContextSetupTest {

	@Test
	public void testCustomerShow() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/operator/login"))
		// .andExpect(MockMvcResultMatchers.view().name("customer/view"))
		// .andExpect(MockMvcResultMatchers.model().attributeExists("customer"))
				.andDo(MockMvcResultHandlers.print()).andReturn();
//		Assert.assertNotNull(result.getModelAndView().getModel().get("customer"));
	}

	@Test
	public void testCustomerAdd() throws Exception {
		MockHttpServletRequestBuilder post = MockMvcRequestBuilders.post("/operator/login");
		post.param("fullName", "测试131231111");
		MvcResult result = mockMvc.perform(post)
				// .andExpect(MockMvcResultMatchers.view().name("customer/view"))
				.andExpect(MockMvcResultMatchers.model().attributeExists("customer"))
				.andDo(MockMvcResultHandlers.print()).andReturn();

		Assert.assertNotNull(result.getModelAndView().getModel().get("customer"));
	}
}
