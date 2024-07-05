# Use a base image with Java 11 and Gradle
FROM adoptopenjdk:11-jdk-hotspot as build

# Set the working directory in the container
WORKDIR /app

# Copy the Gradle build files
COPY build.gradle .
COPY settings.gradle .
COPY gradlew .

# Copy the Gradle wrapper
COPY gradle gradle

# Copy the source code
COPY src src

# Build the application
RUN ./gradlew build

# Create a new image with only the necessary runtime dependencies
FROM adoptopenjdk:11-jre-hotspot

# Set the working directory in the container
WORKDIR /app

# Copy the built application from the previous stage
COPY --from=build /app/build/libs/retenciones-1.0.1.jar ./app.jar

# Expose the desired port(s) for the application
EXPOSE 8080
EXPOSE 5005

# Specify the command to run the application
CMD ["java", "-agentlib:jdwp=transport=dt_socket,address=*:5005,server=y,suspend=n","-Djava.security.egd=file:/dev/./urandom", "-jar", "app.jar"]
