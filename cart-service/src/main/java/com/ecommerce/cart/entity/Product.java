/**
 * 
 */
package com.ecommerce.cart.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author chinnb
 *
 */
@Entity
@Table(name="product")
public class Product implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	Integer id;
	
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name="name")
    private String name;
	
	@Column(name="description")
    private String description;
	
	public Product() {
		
	}
	
	public Product(String name, String description) {
		this.name = name;
		this.description = description;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}  
}
