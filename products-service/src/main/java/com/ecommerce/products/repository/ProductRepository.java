/**
 * 
 */
package com.ecommerce.products.repository;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ecommerce.products.model.Product;

/**
 * @author chinnb
 *
 */
public interface ProductRepository extends MongoRepository<Product, Integer> {
	
	public Product findByName(String firstName);
	
	
    public List<Product> findByCategory(String category);
    
    
}
