/**
 * 
 */
package com.ecommerce.products.model;

import java.io.Serializable;
import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author chinnb
 *
 */
@Document(collection="products")
public class Product implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	private int id;
	@Indexed
	String name;
	@Indexed
	String category;
	BigDecimal price;
	String description;
	
	public Product() {
		
	}
	public Product(String name, String category, BigDecimal price, String description) {
		this.name = name;
		this.category = category;
		this.price = price;
		this.description = description;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}
