FROM openjdk:11
COPY target/url-shortener-0.1.jar url-shortener-0.1.jar
EXPOSE 8080
CMD ["java", "-Xmx128m", "-jar", "url-shortener-0.1.jar"]