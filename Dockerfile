FROM openjdk:8-jre-alpine
VOLUME /tmp
ADD target/api-security.jar app.jar
ADD init.sh init.sh
CMD ["/usr/bin/java", "-jar", "/app.jar", "@init.sh"]
