mvn archetype:generate -DgroupId=com.bala.ecommerce -DartifactId=ecommerce

mvn archetype:generate -DgroupId=com.bala.ecommerce.products -DartifactId=products
	-DarchetypeArtifactId=ecommerce-products -DinteractiveMode=false

mvn archetype:generate -DgroupId=com.bala.ecommerce.cart -DartifactId=cart
	-DarchetypeArtifactId=ecommerce-cart -DinteractiveMode=false

mvn archetype:generate -DgroupId=com.bala.ecommerce.user -DartifactId=user
	-DarchetypeArtifactId=ecommerce-user -DinteractiveMode=false

mvn archetype:generate -DgroupId=com.bala.ecommerce.payment -DartifactId=payment
	-DarchetypeArtifactId=ecommerce-payment -DinteractiveMode=false
	
mvn archetype:generate -DgroupId=com.bala.ecommerce.gateway -DartifactId=apigateway-zuul
	-DarchetypeArtifactId=apigateway-zuul -DinteractiveMode=false
	
mvn archetype:generate -DgroupId=com.bala.ecommerce.discovery -DartifactId=discovery-eureka
	-DarchetypeArtifactId=discovery-eureka -DinteractiveMode=false 
	
mvn archetype:generate -DgroupId=com.bala.ecommerce.auth -DartifactId=auth
	-DarchetypeArtifactId=ecommerce-auth -DinteractiveMode=false	
	
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