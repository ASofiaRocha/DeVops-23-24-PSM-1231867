# CA4 - Part 2: Containerized Environment with Docker

## Table of Contents
* [Introduction](#introduction)
* [Docker Configuration](#docker-configuration)
    * [Dockerfile for `db` Container](#dockerfile-for-db-container)
    * [Dockerfile for `web` Container](#dockerfile-for-web-container)
    * [Docker Compose Configuration](#docker-compose-configuration)
* [Instructions to Build and Run the Docker Images](#instructions-to-build-and-run-the-docker-images)
* [Publishing the Docker Images to Docker Hub](#publishing-the-docker-images-to-docker-hub)
* [Conclusion](#conclusion)
* [Repository Tagging](#repository-tagging)

## Introduction

This assignment involves setting up a containerized environment using Docker to execute the Gradle version of the Spring Basic Tutorial application.
The solution will utilize Docker Compose to create two services/containers: one for running Tomcat and the Spring application (`web`), and the other for executing the H2 server database (`db`).

## Docker Configuration

### Dockerfile for `db` Container

This Dockerfile sets up the H2 database server. Save this file in the `db` directory: `CA4/Part2/db/Dockerfile`.
The H2 database is downloaded and started in the container. The database is exposed on port 8082 for the web application to connect to.

```javascript
FROM gradle:jdk17

RUN apt-get update && \
  apt-get install -y openjdk-17-jdk-headless && \
  apt-get install unzip -y && \
  apt-get install wget -y

RUN mkdir -p /usr/src/app

WORKDIR /usr/src/app/

# Download H2 Database and run it
RUN wget https://repo1.maven.org/maven2/com/h2database/h2/2.2.224/h2-2.2.224.jar -O /opt/h2.jar

EXPOSE 8082
EXPOSE 9092

# Start H2 Server
CMD ["java", "-cp", "/opt/h2.jar", "org.h2.tools.Server", "-tcp", "-tcpAllowOthers", "-tcpPort", "9092", "-web", "-webAllowOthers", "-webPort", "8082", "-ifNotExists"]
```

### Dockerfile for web Container
This Dockerfile sets up the Tomcat server and deploys the Spring application. Save this file in the web directory: CA4/Part2/web/Dockerfile.
The Spring application JAR file is copied to the container, and the application is started on port 8080.

```javascript
FROM gradle:jdk17

# Copy the WAR file to the webapps directory
COPY react-and-spring-data-rest-basic-0.0.1-SNAPSHOT.jar .

EXPOSE 8080

CMD ["java", "-jar", "react-and-spring-data-rest-basic-0.0.1-SNAPSHOT.jar"]
```

### Docker Compose Configuration
The docker-compose.yml file defines the services for the web and db containers.
Save this file in the project directory: CA4/Part2/docker-compose.yml.
The web service depends on the db service and sets the environment variables for the Spring application to connect to the H2 database.

```javascript
version: '3'
services:
  web:
    build: web
    ports:
      - "8080:8080"
    networks:
      default:
        ipv4_address: 192.168.56.10
    depends_on:
      - "db"
  db:
    build: db
    ports:
      - "8082:8082"
      - "9092:9092"
    volumes:
      - ./data:/usr/src/data-backup
    networks:
      default:
        ipv4_address: 192.168.56.11
networks:
  default:
    ipam:
      driver: default
      config:
        - subnet: 192.168.56.0/24
```

## Instructions to Build and Run the Docker Images
### Build the Docker Images
**In the terminal, navigate to the project directory CA4/Part2 and run the following command to build the Docker images:**
```javascript
docker-compose build
```

### Run the Docker Containers
**After building the images, run the Docker containers using the following command:** 
```javascript
docker-compose up
```
### Access the Application
Open a web browser and navigate to http://localhost:8080/basic-0.0.1-SNAPSHOT/ to access the Spring application.
You can also access the H2 database console at http://localhost:8082 and connect to the database jpadb with the JDBC URL jdbc:h2:tcp://192.168.56.11:9092/~/jpadb.

## Publishing the Docker Images to Docker Hub
To publish the Docker images to Docker Hub, follow these steps:

**Log in to Docker Hub using the following command:**
```javascript
docker login
```

**Tag the images with your Docker Hub username and push them to Docker Hub:**

```javascript
# Tag the db image and push it to Docker Hub
docker tag <your-docker-id>/chatserver/db <your-docker-id>/chatserver/db
docker push <your-docker-id>/chatserver/db

# Tag the web image and push it to Docker Hub
docker tag <your-docker-id>/chatserver/web <your-docker-id>/chatserver/web
docker push <your-docker-id>/chatserver/web
```

## Conclusion
This completes the setup of a containerized environment using Docker for the Gradle version of the Spring Basic Tutorial application. 
The application is deployed in two services: one for the Tomcat server and the Spring application, and the other for the H2 database server. 
The Docker Compose configuration defines the services and their dependencies, allowing the application to run in a containerized environment.

## Repository Tagging
After completing the setup, tag your repository with the appropriate tag and push it to the remote repository:

```javascript
git add .
git commit -m "Containerized environment with Docker. Closes #22"
git push origin main
git tag -a ca4-part2 -m "Complete CA4 Part 2"
git push origin --tags
```
