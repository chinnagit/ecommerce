/**
 * 
 */
package com.ecommerce.cart.repository;

import org.springframework.data.repository.CrudRepository;

import com.ecommerce.cart.entity.User;

/**
 * @author chinnb
 *
 */
public interface UserRepository extends CrudRepository<User, Integer>{
//	public User findById(int userId);
	public User findByName(String userName);
}
