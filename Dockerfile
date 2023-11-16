FROM gradle:17
VOLUME /tmp
RUN gradlew build
CMD bootRUN