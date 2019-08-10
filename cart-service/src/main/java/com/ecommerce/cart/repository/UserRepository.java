/**
 * 
 */
package com.ecommerce.cart.repository;

import com.ecommerce.cart.entity.User;
import org.springframework.data.repository.CrudRepository;

/**
 * @author chinnb
 *
 */
public interface UserRepository extends CrudRepository<User, Integer>{
//	public User findById(int userId);
	public User findByName(String userName);
}
