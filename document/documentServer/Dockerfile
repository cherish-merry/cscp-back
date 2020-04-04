From java:8
VOLUME /tmp
ADD target/documentServer-1.0-SNAPSHOT.jar documentServer-1.0-SNAPSHOT.jar
EXPOSE 8000
ENTRYPOINT ["java","-jar","documentServer-1.0-SNAPSHOT.jar","--spring.profiles.active=prod"]