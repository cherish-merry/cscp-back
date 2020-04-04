From java:8
VOLUME /tmp
ADD target/auth-1.0-SNAPSHOT.jar auth-1.0-SNAPSHOT.jar
EXPOSE 8181
ENTRYPOINT ["java","-jar","auth-1.0-SNAPSHOT.jar","--spring.profiles.active=prod"]