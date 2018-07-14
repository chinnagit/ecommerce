
package com.ecommerce.products;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/*
 * https://medium.com/@gustavo.ponce.ch/spring-boot-restful-junit-mockito-hamcrest-eclemma-5add7f725d4e
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ProductsApplication.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductsApiTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

    }

    @Test
    public void verifyGetAllProducts() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/products").accept(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$", hasSize(6)))
            .andDo(print());
    }

    // @Test
    // public void addProduct() throws Exception {
    // mockMvc.perform(MockMvcRequestBuilders.put("/products").accept(MediaType.APPLICATION_JSON)).andDo(print());
    // }

    // @Test
    // public void updateProduct() throws Exception {
    // Product prod = new Product();
    // mockMvc
    // .perform(MockMvcRequestBuilders.post("/todo/").contentType(MediaType.APPLICATION_JSON).content(prod).accept(MediaType.APPLICATION_JSON))
    // .andExpect(jsonPath("$.id").exists()).andExpect(jsonPath("$.name").exists()).andExpect(jsonPath("$.category").exists())
    // .andExpect(jsonPath("$.price").exists()).andDo(print());
    // mockMvc.perform(MockMvcRequestBuilders.post("/products").accept(MediaType.APPLICATION_JSON)).andDo(print());
    // }

    // @Test
    // public void deleteProduct() throws Exception {
    // mockMvc.perform(MockMvcRequestBuilders.delete("/products").accept(MediaType.APPLICATION_JSON)).andDo(print());
    // }

}
