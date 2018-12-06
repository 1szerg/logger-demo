FROM openjdk:8
ADD loggerDemo.jar
RUN bash -c 'touch /loggerDemo.jar'
ENTRYPOINT["java", "-jar", "loggerDemo.jar"]