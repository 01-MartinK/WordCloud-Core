FROM bhakiyaraj7/spring_boot_docker
COPY . /app
WORKDIR /app

EXPOSE 8080
CMD ["./gradlew", "bootRun"]