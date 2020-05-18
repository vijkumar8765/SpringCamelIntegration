FROM java:8
EXPOSE 8080
ADD /target/springcamelintegration-1.1.0.jar springcamelintegration-1.1.0.jar
ENTRYPOINT ["java", "-jar", "springcamelintegration.jar"]
