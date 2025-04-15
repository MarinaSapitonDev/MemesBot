FROM openjdk:19-jdk
EXPOSE 8080
WORKDIR /MemesBot
COPY target/MemesBot.jar MemesBot.jar
ENTRYPOINT ["java", "-jar", "MemesBot.jar"]