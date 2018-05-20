/**
 * 
 */
package com.ecommerce.cart.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author chinnb
 *
 */
@Entity
@Table(name="user")
public class User implements Serializable{
	
		private static final long serialVersionUID = 1L;
//		@Id
//		@GeneratedValue(strategy=GenerationType.AUTO)
		
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
		
//		@OneToMany(targetEntity=Cart.class, fetch = FetchType.EAGER, mappedBy = "user")
		@OneToMany(targetEntity=Cart.class, fetch = FetchType.EAGER, mappedBy = "uid")
		private Set<Cart> cart = new HashSet<Cart>(
				0);
		
		public User() {
			
		}
		
		public User(String name) {
			this.name = name;
		}
		
		public String getName() {
			return name;
		}
		
		public void setName(String name) {
			this.name = name;
		} 
		
		public Set<Cart> getCart() {
			return this.cart;
		}
		
		public void setCart(Set<Cart> cart) {
			 this.cart = cart;
		}

}
