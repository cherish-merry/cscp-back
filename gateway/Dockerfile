From java:8
VOLUME /tmp
ADD target/gateway-1.0-SNAPSHOT.jar gateway-1.0-SNAPSHOT.jar
EXPOSE 8300
ENTRYPOINT ["java","-jar","gateway-1.0-SNAPSHOT.jar","--spring.profiles.active=prod"]