From java:8
VOLUME /tmp
ADD target/config-1.0-SNAPSHOT.jar config-1.0-SNAPSHOT.jar
EXPOSE 8200
ENTRYPOINT ["java","-jar","config-1.0-SNAPSHOT.jar","--spring.profiles.active=prod"]