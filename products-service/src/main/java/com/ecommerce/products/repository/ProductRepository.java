/**
 * 
 */
package com.ecommerce.products.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ecommerce.products.model.Product;

/**
 * @author chinnb
 *
 */
public interface ProductRepository extends MongoRepository<Product, Integer> {

    public List<Product> findByName(String firstName);

    public List<Product> findByCategory(String category);

    // not working public Product findById(String productId);

}
