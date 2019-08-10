/**
 * 
 */
package hello;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

import com.ecommerce.products.model.Product;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author chinnb
 *
 */
public class ProductsApiTest {

    protected Logger logger = Logger.getLogger(ProductsApiTest.class.getName());
    private enum RequestTypesEnum {
        // GET, POST;
        GET("GET"),
        POST("POST"),
        DELETE("DELETE");

        String type;

        RequestTypesEnum(String type) {
            this.type = type;
        }

    }

    private static final String REST_URI = "http://localhost:2222/products/";

    Product product = new Product();

    private static Client client = ClientBuilder.newClient();

    public static void addProduct(Product product) {
        try {

            URL url = new URL(REST_URI);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");

            ObjectMapper mapper = new ObjectMapper();
            // Convert object to JSON string
            String jsonInString = mapper.writeValueAsString(product);
            System.out.println(jsonInString);

            OutputStream os = conn.getOutputStream();
            os.write(jsonInString.getBytes());
            os.flush();

            System.out.println("conn.getResponseCode(): " + conn.getResponseCode());
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

            String output;
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }

            conn.disconnect();

        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }
    }

    public static void getProduct() {

        ObjectMapper mapper = new ObjectMapper();
        String output = genericInvocation(REST_URI, RequestTypesEnum.GET, null);
        try {
            // Convert JSON string to Object
            Product[] products = mapper.readValue(output, Product[].class);

            // Pretty print
            String prettyStaff1 = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(products);
            System.out.println(prettyStaff1);

        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteProduct(String productName) {

        String output = genericInvocation(REST_URI + productName, RequestTypesEnum.DELETE, null);
    }

    public static void addProducts(List<Product> products) {
        try {

            ObjectMapper mapper = new ObjectMapper();
            // Convert object to JSON string
            String jsonInString = mapper.writeValueAsString(products);
            System.out.println(jsonInString);

            String output = genericInvocation(REST_URI, RequestTypesEnum.POST, jsonInString);

        } catch (IOException e) {

            e.printStackTrace();

        }
    }

    private static String genericInvocation(String uri, RequestTypesEnum type, String request) {
        // retry logic
        for (int i = 0; i < 3; i++) {
            try {
                URL url = new URL(uri);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setDoOutput(true);
                conn.setRequestMethod(type.toString());

                if (type.equals(RequestTypesEnum.DELETE) || type.equals(RequestTypesEnum.POST)) {
                    conn.setRequestProperty("Content-Type", "application/json");
                } else if (type.equals(RequestTypesEnum.GET)) {
                    conn.setRequestProperty("Accept", "application/json");
                }

                if (request != null) {
                    OutputStream os = conn.getOutputStream();
                    os.write(request.getBytes());
                    os.flush();
                }

                if (conn.getResponseCode() != 200 && i == 2) {
                    throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
                }

                if (type.equals(RequestTypesEnum.GET)) {
                    BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
                    String output;
                    StringBuilder sb = new StringBuilder();
                    // System.out.println("Output from Server .... \n");
                    while ((output = br.readLine()) != null) {
                        sb.append(output);
                        // System.out.println(output);
                    }
                    return sb.toString();
                } else if (type.equals(RequestTypesEnum.DELETE) || type.equals(RequestTypesEnum.POST)) {

                    BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

                    String output;

                    while ((output = br.readLine()) != null) {
                        System.out.println(output);
                    }
                    System.out.println("Output from Server .... " + output);
                    return output;

                }
                conn.disconnect();

            } catch (MalformedURLException e) {

                e.printStackTrace();

            } catch (IOException e) {

                e.printStackTrace();

            }
        }
        return null;
    }

    public static Product getJsonProduct(String name) {
        return client.target(REST_URI).path(String.valueOf(name)).request(MediaType.APPLICATION_JSON).get(Product.class);
    }

    public static void main(String[] args) {

        // Product prod = getJsonProduct("Nikejacket");
        // System.out.println(prod);

        Product product1 = new Product(70, "Adidas", "shoes", new BigDecimal(45.99), "boys shoe with size 2`");
        Product product2 = new Product(80, "Nike", "shoes", new BigDecimal(125.59), "Men shoe with size 12`");
        Product product3 = new Product(90, "Rebok", "shoes", new BigDecimal(100.99), "Women shoe with size 8`");
        Product product4 = new Product(100, "Under Armour", "shoes", new BigDecimal(55.99), "boys shoe with size 2`");
        Product product5 = new Product(110, "Nike", "shoes", new BigDecimal(155.59), "Men shoe with size 12`");
        Product product6 = new Product(120, "Rebok", "shoes", new BigDecimal(110.99), "Women shoe with size 8`");

        List<Product> products = new ArrayList<Product>();
        products.add(product1);
        products.add(product2);
        products.add(product3);
        products.add(product4);
        products.add(product5);
        products.add(product6);

        addProducts(products);
        // addProduct(product);

        getProduct();
        deleteProduct("Nike");
        getProduct();

    }

}
