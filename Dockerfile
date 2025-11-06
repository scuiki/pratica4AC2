FROM openjdk:17

# Set the working directory in the container
WORKDIR /pratica4ac2

COPY target/*.jar /pratica4ac2/pratica4ac2-0.0.1-SNAPSHOT.jar

# Expose the port that your application will run on
EXPOSE 8585

# Specify the command to run on container start
CMD ["java", "-jar", "pratica4ac2-0.0.1-SNAPSHOT.jar"]
