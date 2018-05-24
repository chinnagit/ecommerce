FROM openjdk:8-jre
MAINTAINER Bala <bala@email.com>

ENTRYPOINT ["/usr/bin/java", "-jar", "../target/zipkin-service.jar"]

# Add Maven dependencies (not shaded into the artifact; Docker-cached)
#ADD target/lib           /usr/share/myservice/lib
# Add the service itself
ARG JAR_FILE
ADD target/${JAR_FILE} ../target/zipkin-service.jar
