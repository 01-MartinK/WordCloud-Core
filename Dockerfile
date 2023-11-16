FROM amazoncorretto:17
VOLUME /tmp
COPY target/main.jar main.jar
ENTRYPOINT ["java","-jar","/main.jar"]