/**
 * 
 */
package com.ecommerce.products.repository;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ecommerce.products.model.User;
import com.ecommerce.products.model.Users;

/**
 * @author chinnb
 *
 */
public interface UserRepository {
//extends MongoRepository<Users, String> {
	
	public Users findByName(String firstName);
    public List<Users> findByUserId(String userId);
    

}
