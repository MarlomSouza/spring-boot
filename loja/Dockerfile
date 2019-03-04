
FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAVA_OPTS
ENV JAVA_OPTS=$JAVA_OPTS
ADD target/loja-0.0.1-SNAPSHOT.jar loja.jar
EXPOSE 8080
# ENTRYPOINT exec java $JAVA_OPTS -jar loja.jar
# For Spring-Boot project, use the entrypoint below to reduce Tomcat startup time.
ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar loja.jar

FROM tomcat:8-jre8
COPY target/loja-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/