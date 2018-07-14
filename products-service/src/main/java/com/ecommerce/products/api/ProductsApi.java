/**
 * 
 */
package com.ecommerce.products.api;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.products.model.Company;
import com.ecommerce.products.model.Contact;
import com.ecommerce.products.model.Product;
import com.ecommerce.products.model.Users;
import com.ecommerce.products.repository.CompanyRepository;
import com.ecommerce.products.repository.ProductRepository;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author chinnb
 *
 */

@RestController
public class ProductsApi {

    // @Autowired
    // private UserRepository repository;
    @Autowired
    ProductRepository productRepo;

    @Autowired
    CompanyRepository companyRepo;

    private List<Product> products;

    protected Logger logger = Logger.getLogger(ProductsApi.class.getName());

    Boolean isMongoDb = false;

    public ProductsApi() {
        ResourceBundle bundle = ResourceBundle.getBundle("config"); // creates bundle from file
                                                                    // `config.properties`
        // List<String> keys = (List<String>) bundle.getKeys(); //gets the list of keys found in the
        // bundle (file)
        isMongoDb = new Boolean(bundle.getString("ismongodb")); // gets the value for the key
                                                                // `mykey`

        products = new ArrayList<>();
        products.add(new Product(10, "iphone 3", "phones", new BigDecimal(299.99), "old model phone from Apple"));
        products.add(new Product(20, "iphone 8", "phones", new BigDecimal(599.99), "new model phone from Apple"));
        products.add(new Product(30, "switch", "gaming console", new BigDecimal(399.99), "Gaming console from Nintndo"));
        products.add(new Product(40, "Xbox", "gaming console", new BigDecimal(499.99), "Gaming console from microsoft"));
    }

    /**
     * Using SpEL for conditional caching - only cache method executions when
     * the name is equal to 'Joshua'
     */

    @RequestMapping(value = "/products/{name}", method = RequestMethod.GET)
    @Cacheable(value = "productByName", condition = "'Nikejacket'.equals(#name)")
    @ApiOperation(value = "Find pet by ID", notes = "Returns a pet when ID < 10.  ID > 10 or nonintegers will simulate API error conditions", response = Product.class
    // , authorizations = @Authorization(value = "api_key"
    )
    @ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid name supplied"), @ApiResponse(code = 404, message = "Product not found") })
    public List<Product> findByName(@PathVariable("name") String name) {
        logger.info(String.format("Product.findByName(%s)", name));
        if (isMongoDb) {
            return productRepo.findByName(name);
        } else {
            return products.stream().filter(it -> it.getName().equals(name)).collect(Collectors.toList());
        }
    }

    /**
     * Using SpEL for conditional caching - only cache method executions when
     * the category is equal to 'men'
     */
    @RequestMapping(value = "/products/category/{category}", method = RequestMethod.GET)
    @Cacheable(value = "productByCategory", condition = "'men'.equals(#category)")
    public List<Product> findByCatefory(@PathVariable("category") String category) {
        logger.info(String.format("Product.findByCategory(%s)", category));
        if (isMongoDb) {
            return productRepo.findByCategory(category);
        } else {
            return products.stream().filter(it -> it.getCategory().equals(category)).collect(Collectors.toList());
        }
    }

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    // @Cacheable(value = "product", key = "#name", unless = "#result == null")
    // @Cacheable("allproducts")
    public List<Product> findAll() {
        System.out.println("############################ Product.findAll() ###################################");
        logger.info("############################ Product.findAll() ################################### " + isMongoDb);
        if (isMongoDb)
            return productRepo.findAll();
        else
            return products;
    }

    @RequestMapping(value = "/products/", method = RequestMethod.PUT)
    public void updateProduct(@RequestBody Product product) {
        System.out.println("updating product");
        if (isMongoDb) {
            List<Product> productOrgnl = productRepo.findByName(product.getName());
            productOrgnl.get(0).setPrice(product.getPrice());
            productRepo.save(product);
        }

    }

    // @RequestMapping(value = "/products/", method = RequestMethod.POST)
    // public void addProduct(@RequestBody Product product) {
    // System.out.println("adding product");
    // if (isMongoDb) {
    // productRepo.save(product);
    // }
    //
    // }

    @RequestMapping(value = "/products/", method = RequestMethod.POST)
    public void addProducts(@RequestBody List<Product> products) {
        System.out.println("adding product");
        if (isMongoDb) {
            productRepo.save(products);
        }

    }

    @RequestMapping(value = "/products/{productName}", method = RequestMethod.DELETE)
    public void deleteProduct(@PathVariable("productName") String productName) throws Exception {
        System.out.println("deleting product");
        if (isMongoDb) {
            List<Product> products = null;
            try {
                products = productRepo.findByName(productName);
            } catch (Exception exxp) {
                throw new Exception("Product with name: " + productName + " not found");
            }
            if (products != null) {
                for (Product product : products) {
                    productRepo.delete(product);
                }

            } else {
                System.out.println(" product with name: " + productName + " not found");
            }
        }

    }

    @RequestMapping(value = "/company", method = RequestMethod.GET)
    public List<Company> findAllCompanies() {
        List<Company> companies = null;
        System.out.println("############################ Product.findAllCompanies() ###################################");
        logger.info("############################ Product.findAllCompanies() ###################################");
        List<Users> users = new ArrayList<Users>();
        try {
            List<Product> prods = productRepo.findAll();
            System.out.println("############################ productRepo.findAll() ################################### " + prods.size());
            mongoOperations();
            companies = companyRepo.findAll();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return companies;
    }

    public void mongoOperations() throws Exception {
        // clear database
        System.out.println("==========Delete all company entities==========");
        companyRepo.deleteAll();

        // save Documents to MongoDB
        System.out.println("==========Save list of company entities==========");
        companyRepo.save(Arrays.asList(
            // Apple company & products
            new Company(1, "Apple",

                // list of products
                Arrays.asList(new Product(10, "A-123", "Iphone 7", new BigDecimal(649.00).setScale(2, BigDecimal.ROUND_HALF_EVEN), "FREE shipping"),
                    new Product(20, "A-456", "IPadPro", new BigDecimal(417.67).setScale(2, BigDecimal.ROUND_HALF_EVEN), "FREE shipping")),

                // contact
                new Contact("Cupertino, CA 95014", "1-408-996-1010")),

            // Samsung company & products
            new Company(2, "Samsung",

                // list of products
                Arrays.asList(new Product(30, "S-012", "GalaxyJ7", new BigDecimal(219.00).setScale(2, BigDecimal.ROUND_HALF_EVEN), "FREE shipping"),
                    new Product(40, "S-456", "GalaxyTabA", new BigDecimal(299.99).setScale(2, BigDecimal.ROUND_HALF_EVEN), " FREE shipping")),

                // contact
                new Contact("Seocho District, Seoul, South Korea", "+82-2-2053-3000"))));
        // initial List Companies variable
        List<Company> companies = null;

        // fetch all documents
        System.out.println("==========Fetch aLL companies:==========");
        companies = companyRepo.findAll();
        companies.forEach(System.out::println);

        // find Company by name
        System.out.println("==========Find a company by name:==========");
        companies = companyRepo.findByName("Samsung");
        companies.forEach(System.out::println);

        // find Company by address
        System.out.println("==========Find a company by address:==========");
        companies = companyRepo.findByAddress("Cupertino, CA 95014");
        companies.forEach(System.out::println);
    }

}
