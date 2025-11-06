FROM openjdk:17

# Set the working directory in the container
WORKDIR /pratica4AC2

COPY target/*.jar /pratica4AC2/pratica4AC2-0.0.1-SNAPSHOT.jar

# Expose the port that your application will run on
EXPOSE 8585

# Specify the command to run on container start
CMD ["java", "-jar", "pratica4AC2-0.0.1-SNAPSHOT.jar"]