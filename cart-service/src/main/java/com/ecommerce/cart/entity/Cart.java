/**
 * 
 */
package com.ecommerce.cart.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author chinnb
 *
 */
@Entity
@Table(name="cart")
public class Cart implements Serializable{
	
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
		
		@Column(name="uid")
		private Integer uid;
//		@ManyToOne(fetch = FetchType.EAGER)
//		@JoinColumn(name = "uid", nullable = false)
//	    private User user;  
		
		@ManyToOne(fetch = FetchType.EAGER)
		@JoinColumn(name="item_id" )
	    private Product product;
		
		@Column(name="price")	
		private BigDecimal price;
		
		@Column(name="quantity")	
		private Integer quantity;
		
		public Cart() {
			
		}
		
		public Cart(int uid, int itemId, BigDecimal price, int quantity) {
//			this.user = new User("xxx");
//			this.itemId = itemId;
			this.price = price;
			this.quantity = quantity;
		}
		
//		public User getUser() {
//			return user;
//		}
//		
//		public void setUser(User user) {
//			this.user = user;
//		}
		
		public Integer getUid() {
			return uid;
		}

		public void setUid(Integer uid) {
			this.uid = uid;
		}

//		public int getItemId() {
//			return itemId;
//		}
//		public void setItemId(int itemId) {
//			this.itemId = itemId;
//		}
		
		public Product getProduct() {
			return product;
		}

		public void setProduct(Product product) {
			this.product = product;
		}

		public BigDecimal getPrice() {
			return price;
		}
		
		public void setPrice(BigDecimal price) {
			this.price = price;
		}
		
		public Integer getQuantity() {
			return quantity;
		}
		
		public void setQuantity(Integer quantity) {
			this.quantity = quantity;
		}
		
//		@ManyToOne(fetch = FetchType.EAGER)
//		public List<User> getUser() {
//			return this.user;
//		}
//		
//		public void setUser(List<User> user) {
//			 this.user = user;
//		}
}
