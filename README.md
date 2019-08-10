# ecommerce
Micro service based distributed system build using spring cloud and zuul.

This example to demonstrate how to build java micro services on spring cloud 
using netflix api gate way ZUUL, eureka as service discovery, zipkin as distributed 
tracing system.  


Requirements:
1. Install java.
2. Install maven.
3. Install mongodb.
4. Install redis.

How to start the entire system.

1. open new terminal and start mongodb 
<mongo install location>/bin/mongod

2. open new terminal and start redis 
<redis install path>/bin/redis-server 

3. open new terminal and get the source code -- clone https://github.com/chinnagit/ecommerce
  a. cd ecommerce and build the system.
  b. mvn clean install -DskipTets
4. build the application 
    ./run-service.sh install
5. run the services.
   ./run-service.sh start.
   
6. check the logs, wait for a while till all the services are up and running.

7. open the browser and access the application using url 
http://localhost:4200/login

credentials are : john/123

go to clothing and/or shoes tabs, add items to cart and click on cart. 

authentication is implemented using oauth2 and jwt token mechanism. security-oauth-server
is authentication server, which authenticate the user credentials and generate the
jwt token, and using this token micro services will be accessed through zuul 
gateway server, which is the gate way to access any service in the distributed 
system.

For complete architecture of this system, pls refer https://github.com/chinnagit/ecommerce/blob/master/ecommerce.pptx


8. This system can also be run using docker.

Requirements.
A. install docker.
B. Install java and maven.
C. clone the code and run -- mvn clean install -DskipTests
D. docker-compose up.


 





