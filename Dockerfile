FROM amazoncorretto:11
LABEL maintainer="sr.funk.sensei@gmail.com"
COPY target/cinema-0.2.0.jar cccinema.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/cccinema.jar"]