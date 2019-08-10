FROM openjdk:8-jre
MAINTAINER Bala <bala@email.com>

ENTRYPOINT ["java", "-jar", "../target/products-service.jar"]

# Add Maven dependencies (not shaded into the artifact; Docker-cached)
#ADD target/lib           /usr/share/myservice/lib
# Add the service itself
ADD target/products-service.jar ../target/products-service.jar
