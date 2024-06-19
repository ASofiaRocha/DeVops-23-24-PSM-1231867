# CA5 - Part 1 - Continuous Integration and Delivery with Jenkins

## Table of Contents
* [Introduction](#introduction)
* [Setting Up Jenkins](#setting-up-jenkins)
* [Pipeline for Gradle Basic Demo](#pipeline-for-gradle-basic-demo)
    * [Creating the Jenkinsfile](#creating-the-jenkinsfile)
    * [Configuring the Job in Jenkins](#configuring-the-job-in-jenkins)
    * [Running the Pipeline](#running-the-pipeline)
* [Pipeline for Spring Boot Application](#pipeline-for-spring-boot-application)
    * [Creating the Jenkinsfile](#creating-the-jenkinsfile-2)
    * [Configuring the Job in Jenkins](#configuring-the-job-in-jenkins-2)
    * [Running the Pipeline](#running-the-pipeline-2)
* [Conclusion](#conclusion)

## Introduction
This document describes the process of setting up Jenkins pipelines for building, testing, and publishing Docker images for two different applications: 
a basic Gradle application and a Spring Boot application.
The pipelines are configured to automate the continuous integration and delivery (CI/CD) process.

## Setting Up Jenkins
### Running Jenkins in Docker
To run Jenkins in Docker, follow these steps:

1. Pull the Jenkins Docker image:

 ```javascript
    docker pull jenkins/jenkins:lts-jdk11
```

2. Run the Jenkins container:

```javascript
    docker run -d -p 8080:8080 -p 50000:50000 -v jenkins-data:/var/jenkins_home --name=jenkins jenkins/jenkins:lts-jdk11
 ```

If you encounter a conflict error because the container name is already in use, remove the existing container:

```javascript
    docker stop jenkins
    docker rm jenkins
```

### Then, run the Jenkins container again:

```javascript
    docker run -d -p 8080:8080 -p 50000:50000 -v jenkins-data:/var/jenkins_home --name=jenkins jenkins/jenkins:lts-jdk11
```

### Running Jenkins with WAR File
To run Jenkins directly using the WAR file:

1. Download the Jenkins WAR file from the official Jenkins website.

2. Run the Jenkins WAR file with a specific port:

```javascript
    java -jar jenkins.war --httpPort=8081
```
This will start Jenkins on port 8081.


## Pipeline for Gradle Basic Demo

### Creating the Jenkinsfile
Create a file named `Jenkinsfile` in the project directory with the following content:

```javascript
pipeline {
  agent any

  stages {
    stage('Checkout') {
      steps {
        echo 'Checking out...'
        git branch: 'main', credentialsId: 'jenkins', url: 'https://github.com/ASofiaRocha/DeVops-23-24-PSM-1231867.git'
      }
    }
    stage('Assemble') {
      steps {
        dir('CA2/Part1/gradle_basic_demo') {
          echo 'Assembling...'
          sh 'chmod +x gradlew'
          sh './gradlew clean assemble'
        }
      }
    }
    stage('Test') {
      steps {
        dir('CA2/Part1/gradle_basic_demo') {
          echo 'Testing...'
          sh './gradlew test'
          junit 'build/test-results/test/*.xml'
        }
      }
    }
    stage('Archive') {
      steps {
        dir('CA2/Part1/gradle_basic_demo') {
          echo 'Archiving...'
          archiveArtifacts 'build/distributions/*'
        }
      }
    }
  }
}
```

### Configuring the Job in Jenkins
1. Access Jenkins at http://localhost:8080.
2. Create a new pipeline job.
3. Configure the Git repository:
- In "Pipeline", select "Pipeline script from SCM".
- In "SCM", select "Git" and enter the repository URL: https://github.com/ASofiaRocha/DeVops-23-24-PSM-1231867.git.
- In "Script Path", enter ca5/gradle_basic_demo/Jenkinsfile

### Running the Pipeline
Save the configuration and run the job.
Check that all stages (Checkout, Assemble, Test, Archive) complete successfully.

## Pipeline for Spring Boot Application

### Creating the Jenkinsfile
Create a file named Jenkinsfile in the project directory with the following content:

```javascript
pipeline {
  agent any

  environment {
    DOCKERHUB_TOKEN = credentials('CA5')
    IMAGE_NAME = "${env.DOCKERHUB_TOKEN_USR}/CA5-jenkins-docker-react-basic"
  }

  stages {
    stage('Checkout') {
      steps {
        echo 'Checking out...'
        git branch: 'main', credentialsId: 'jenkins', url: 'https://github.com/ASofiaRocha/DeVops-23-24-PSM-1231867.git'
      }
    }
    stage('Assemble') {
      steps {
        dir('CA2/Part2/react-and-spring-data-rest-basic') {
          echo 'Assembling...'
          sh 'chmod +x gradlew'
          sh './gradlew clean assemble'
        }
      }
    }
    stage('Test') {
      steps {
        dir('CA2/Part2/react-and-spring-data-rest-basic') {
          echo 'Testing...'
          sh './gradlew test'
          junit 'build/test-results/test/*.xml'
        }
      }
    }
    stage('Archive') {
      steps {
        dir('CA2/Part2/react-and-spring-data-rest-basic') {
          echo 'Archiving...'
          archiveArtifacts 'build/distributions/*'
        }
      }
    }
  }
}

```

### Configuring the Job in Jenkins
1. Create a new pipeline job for the Spring Boot project.
2.  Configure the Git repository:
- In "Pipeline", select "Pipeline script from SCM".
- In "SCM", select "Git" and enter the repository URL: https://github.com/ASofiaRocha/DeVops-23-24-PSM-1231867.git
- In "Script Path", enter ca5/react-and-spring-data-rest-basic/Jenkinsfile

### Running the Pipeline
Save the configuration and run the job.
Check that all stages (Checkout, Assemble, Test, Javadoc, Archive, Publish Image) complete successfully.

## Conclusion
This document described the process of setting up Jenkins pipelines for building and publishing Docker images for two different applications. 
The pipelines automate the continuous integration and delivery process, ensuring that the applications can be easily tested and deployed.

#### Tags and Commits
Add the README.md file and commit the changes:

```javascript
git add README.md
git commit -m "Added README.md file. Closes #32"
git tag ca5-part1
git push --tags
```