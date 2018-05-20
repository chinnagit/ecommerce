/**
 * 
 */
package com.ecommerce.cart.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ecommerce.cart.entity.Cart;

/**
 * @author chinnb
 *
 */
public interface CartRepository extends CrudRepository<Cart, Integer>{
//	public List<Cart> findByUser(int userId);
}
