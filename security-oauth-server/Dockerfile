FROM openjdk:8-jre
MAINTAINER Bala <bala@email.com>

ENTRYPOINT ["java", "-jar", "../target/security-oauth-server.jar"]

# Add Maven dependencies (not shaded into the artifact; Docker-cached)
#ADD target/lib           /usr/share/myservice/lib
# Add the service itself
ADD target/security-oauth-server.jar ../target/security-oauth-server.jar
