#!/bin/bash
#run the micro services and angular client
	
if [ ! -d ./logs ]
 then  
	echo "creating logs directory";
	mkdir logs; 
fi 

install()
{
	mvn clean install -DskipTests
	cd client/ecommerce-ui-jwt
	npm install
	cd ../..
}

start_all()
{
	rm logs/services.pid
	start_eureka_discover_service
	start_auth_server_service
	start_auth_reource_service
	start_products_service
	start_user_service
    start_client
	
}

stop_all()
{
	echo "stopping all"
	while IFS= read -r line
	do    
    		echo "killing PID: "$line
		kill -9 $line
	done < logs/services.pid
	#rm logs/services.pid
	#kill the client
	echo "stopping the angular client"
	kill $(lsof -t -i:4200)
}

start_client()
{
	cd ./client/ecommerce-ui-jwt
	ng serve > ../../logs/client.log 2>&1 &
	echo $! >> ../../logs/services.pid
	cd ../..
}
start_eureka_discover_service() 
{
	echo 'starting discovery service'
	cd ./discovery-eureka-service

	#docker run -it --rm -v "$PWD":/app -w /app demo/maven:3.3-jdk-8 java -jar target/discovery-eureka-service.jar > ../logs/discovery-service.log 2>&1 &
	#docker run -d -it --rm -v "$PWD":/app -w /app demo/maven:3.3-jdk-8 java -jar target/discovery-eureka-service.jar
	mvn spring-boot:run > ../logs/discovery-service.log 2>&1 &

	echo $! >> ../logs/services.pid 
	cd ..
}

start_auth_server_service() 
{
	cd ./security-oauth-server

	mvn spring-boot:run > ../logs/oauth-server-service.log 2>&1 &
    #docker run -d -it --rm -v "$PWD":/app -w /app demo/maven:3.3-jdk-8 java -jar target/security-oauth-server.jar
	echo $! >> ../logs/services.pid 
	cd ..
}

start_auth_reource_service() 
{       
         cd ./gateway-oauth-resource 
       
         mvn spring-boot:run > ../logs/resource-service.log 2>&1 &
         
         echo $! >> ../logs/services.pid
	 cd ..
 }

start_products_service() 
{       
         cd ./products-service 
       
         mvn spring-boot:run > ../logs/products-service.log 2>&1 &
         
         echo $! >> ../logs/services.pid
	 cd ..
 }

start_user_service() 
{       
         cd ./user-service 
       
         mvn spring-boot:run > ../logs/user-service.log 2>&1 &
         
         echo $! >> ../logs/services.pid
	 cd ..
 }

if [ $# == 0 ]; then
	echo " run_services.sh [install/start/stop] [all/service name]"
elif [ $# == 1 ]; then 
	if [ "$1" == "install" ]; then
		echo " runs mvn clean install for services and npm install for the client"
		install
	elif [ "$1" == "start" ]; then
		echo " starting all services"
		start_all
	elif [ "$1" == "stop" ]; then
		echo " stopping all services"
		stop_all
	else
		echo "invalid parameter supplied"
	fi
elif [ $# == 2 ]; then
	if [ "$1" == "start" ]; then
		echo "strating service"
		#start_all
	elif [ "$1" == "stop" ]; then
		echo " stopping service"
		#stop_all
	fi
fi
	
