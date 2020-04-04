From java:8
VOLUME /tmp
ADD target/userServer-1.0-SNAPSHOT.jar userServer-1.0-SNAPSHOT.jar
EXPOSE 8100
ENTRYPOINT ["java","-jar","userServer-1.0-SNAPSHOT.jar","--spring.profiles.active=prod"]