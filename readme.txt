mvn archetype:generate -DgroupId=com.ecommerce -DartifactId=ecommerce

mvn archetype:generate -DgroupId=com.ecommerce.products -DartifactId=products
	-DarchetypeArtifactId=ecommerce-products -DinteractiveMode=false

mvn archetype:generate -DgroupId=com.ecommerce.cart -DartifactId=cart
	-DarchetypeArtifactId=ecommerce-cart -DinteractiveMode=false

mvn archetype:generate -DgroupId=com.ecommerce.user -DartifactId=user
	-DarchetypeArtifactId=ecommerce-user -DinteractiveMode=false

mvn archetype:generate -DgroupId=com.ecommerce.payment -DartifactId=payment
	-DarchetypeArtifactId=ecommerce-payment -DinteractiveMode=false
	
mvn archetype:generate -DgroupId=com.ecommerce.gateway -DartifactId=apigateway-zuul
	-DarchetypeArtifactId=apigateway-zuul -DinteractiveMode=false
	
mvn archetype:generate -DgroupId=com.ecommerce.discovery -DartifactId=discovery-eureka
	-DarchetypeArtifactId=discovery-eureka -DinteractiveMode=false 
	
mvn archetype:generate -DgroupId=com.ecommerce.auth -DartifactId=auth
	-DarchetypeArtifactId=ecommerce-auth -DinteractiveMode=false	
	
mvn archetype:generate -DgroupId=com.ecommerce.java.client -DartifactId=javarestclient
	-DarchetypeArtifactId=java-rest-client -DinteractiveMode=false
	
for hotswapping run time refer -- https://dzone.com/articles/hot-swap-java-bytecode-on-runtime

1. security-oauth-server -- authentication server which authenticate and provides token
2. gateway-oauth-resource -- is gateway and authorization server, delegates calls to micro services only requests received with valid jwt token.
3. micro services -- heart beat with authorization server and allows invoke apis only when requests received with valid jwt token.
4. client -- ecommerce-ui-jwt-implicit -- login using user credentials and obtain jwt token, authorizes from authorization server using jwt token.
is written in angular4

5. for zipkin configuration refer -- http://www.baeldung.com/tracing-services-with-zipkin

In gradle way

6. converting maven to gradle -- gradle init

7. gradle clean build -x test

for each sub project open the separate command line and run -- gradle bootRun

To run the product service make sure redis, mongodb, zipkin, eureka services are up

8. run mongo db with custom path
mongod --dbpath C:\mongo-data\training

swagger.json file will be generated when you run, take this file to client project and generate typescript angular code (refer https://github.com/swagger-maven-plugin/swagger-maven-plugin for more help on swagger and maven plugin)

mvn clean package -DskipTests 



once you launch the application, you can see swagger information, refer https://springframework.guru/spring-boot-restful-api-documentation-with-swagger-2/ 

http://localhost:2222/swagger-ui.html

or 

http://localhost:2222/v2/api-docs


A. How to start application.

start the services from different command prompts 

1. start eureka discovery service.
2. start authentication service.
3. start gateway/authorization service.
4. start product/catalog service.
5. start client project from C:\tutorials\casestudy\ecommerce\client\ecommerce-ui-jwt 
npm start.
access the application using http://localhost:8085






