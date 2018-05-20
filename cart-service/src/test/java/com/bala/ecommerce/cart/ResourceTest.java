package com.bala.ecommerce.cart;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.http.MediaType;

import com.ecommerce.cart.api.CartApi;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration
public class ResourceTest {

	 @Autowired
	    private WebApplicationContext wac;
	    private MockMvc mockMvc;

	    @Configuration
	    @EnableAutoConfiguration
	    public static class Config {
	        @Bean
	        public CartApi apiController() {
	            return new CartApi();
	        }
	    }

	    @Before
	    public void setUp() throws Exception {
	        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	    }

	    @Test
	    public void testGetSummary() throws Exception {
	    	mockMvc.perform(get("/cart/")
	    			.accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//            .andExpect(jsonPath("$.first", is(1)))
    ;
//	    	mockMvc.perform(get("/api/summary").accept(MediaType.APPLICATION_JSON))
//            .andDo(print())
//            .andExpect(status().isOk())
//            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//            .andExpect(jsonPath("$.first", is(1)))
//    ;
//}
	    }
}
