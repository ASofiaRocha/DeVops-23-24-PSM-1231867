# CA2 - Gradle Conversion Project

## Table of Contents
* [Introduction](#Introduction)
* [Setup](#Setup)
    * [Create New Branch](#Create-New-Branch)
    * [Delete src Folder](#Delete-src-Folder)
    * [Copy Tutorial Code](#Copy-Tutorial-Code)
    * [Delete src Folder](#Delete-src-Folder)
    * [Copy src Folder and tutorial code](#Copy-the-src-folder-and-tutorial-code)
    * [Experiment with the Application](#Experiment-with-the-Application)
    * [Add Frontend Plugin](#Add-Frontend-Plugin)
    * [Add Gradle Plugin](#Add-Gradle-Plugin)
    * [Update Scripts in package.json](#Update-Scripts-in-package.json)
    * [Build the Project](#Build-the-Project)
    * [Configure Plugin in build.gradle](#Configure-Plugin-in-build.gradle)
    * [Update Scripts in package.json](#Update-Scripts-in-package.json)
    * [Build the Project and Run the Application](#Build-the-Project-and-Run-the-Application)
    * [Add Gradle Tasks](#Add-Gradle-Tasks)
    * [Run the Application](#Run-the-Application)
    * [Add README.md and Commit with tag ca2-part2](#Commit-and-Merge)
  * [Alternative Solution](#Alternative-Solution)
* [Conclusion](#Conclusion)


## Introduction
This repository contains the code for a React.js and Spring Data REST application. 
The project is structured to follow a specific workflow using Git for version control. 
The goal of Part 2 of this assignment is to convert the basic version of the Tutorial application to Gradle (instead of Maven).

## Setup
- Use https://start.spring.io to start a new Gradle Spring Boot project with the dependencies: Rest Repositories, Thymeleaf, JPA, H2. Download the generated zip file.
- Extract the generated zip file inside the folder CA2/Part2/ of your repository.

```javascript
gradle init
```
### Create New Branch
```javascript
git checkout -b tut-basic-gradle
```

### Delete src Folder
```javascript
rm -rf src
```

### Copy src Folder and tutorial code
- Copy the src folder (and all its subfolders) from the basic folder of the tutorial into this new folder. 
```javascript
cp -r C:\Users\Public\Prof\tut-react-and-spring-data-rest\basic\src .
```
- Also, copy the files webpack.config.js and package.json. 
```javascript
cp C:\Users\Public\Prof\tut-react-and-spring-data-rest\basic\webpack.config.js .
```
- Delete the folder src/main/resources/static/built/.
```javascript
rm -rf src/main/resources/static/built/
```

### Experiment with the Application
- Try to run the application. It should work as before.
```javascript
./gradlew bootRun
```

### Add Frontend Plugin
- Add the frontend plugin to the build.gradle file.
```javascript
plugins {
  id "org.siouan.frontend-jdk17" version "8.0.0"
}
```

### Add Gradle Plugin
- Add the Gradle plugin to the build.gradle file.
```javascript
frontend {
    nodeVersion = "16.20.2"
    assembleScript = "run build"
    cleanScript = "run clean"
    checkScript = "run check"
}
```

### Update Scripts in package.json
- Update the scripts in the package.json file.
```javascript
"scripts": {
  "watch": "webpack --watch -d --output ./target/classes/static/built/bundle.js",
          "webpack": "webpack",
          "build": "npm run webpack",
          "check": "echo Checking frontend",
          "clean": "echo Cleaning frontend",
          "lint": "echo Linting frontend",
          "test": "echo Testing frontend"
},
```

### Build the Project
- Build the project and run the application.
```javascript
./gradlew build
./gradlew bootRun
```

### Configure Plugin in build.gradle
- Configure the plugin in the build.gradle file.
```javascript
frontend {
    nodeVersion = "16.20.2"
    assembleScript = "run build"
    cleanScript = "run clean"
    checkScript = "run check"
}
```

### Update Scripts in package.json
- Update the scripts in the package.json file.
```javascript
"scripts": {
 "webpack": "webpack",
 "build": "npm run webpack",
 "check": "echo Checking frontend",
 "clean": "echo Cleaning frontend",
 "lint": "echo Linting frontend",
 "test": "echo Testing frontend"
 },
```

### Build the Project and Run the Application
- Build the project and run the application.
```javascript
./gradlew build
./gradlew bootRun
```

### Add Gradle Tasks
- Add the following Gradle tasks to the build.gradle file.
```javascript
task copyJarToDist(type: Copy) {
	from jar
	into projectDir.path + '/dist'
}

task deleteWebpackFiles(type: Delete) {
    delete 'src/main/resources/static/built/'
}
```

### Run the Application
- Run the application.
```javascript
./gradlew bootRun
```

### Commit and Merge
- Commit the changes and merge the branch into main.
```javascript
git add .
git commit -m "Completed Part 2 of the assignment"
git checkout main
git merge tut-basic-gradle
```

### Add README.md and Commit with tag ca2-part2
- Add a README.md file and commit the changes with the tag ca2-part2.
```javascript
git add .
git commit -m "Added README.md file"
git tag ca2-part2
git push --tags
```

## Alternative Solution - Ant 
- Try to run the application. It should work as before 
```javascript
ant run
```
- Add the frontend plugin
```javascript
<target name="frontend" description="Frontend build">
    <exec executable="npm" dir="." failonerror="true">
        <arg value="run"/>
        <arg value="build"/>
    </exec>
</target>
```
- Add the Ant build file to the build.xml file
```javascript
<project name="tut-basic-ant" default="build" basedir=".">
    <description>
        simple example build file
    </description>
    <!-- set global properties for this build -->
    <property name="src" location="src"/>
    <property name="build" location="build"/>

    <target name="init">
        <!-- Create the build directory structure used by compile -->
        <mkdir dir="${build}"/>
    </target>

    <target name="compile" depends="init" description="compile the source">
        <!-- Compile the java code from ${src} into ${build} -->
        <javac srcdir="${src}" destdir="${build}"/>
    </target>

    <target name="build" depends="compile" description="generate the distribution">
        <!-- Create the distribution directory -->
        <mkdir dir="dist"/>

        <!-- Put everything in ${build} into the MyProject-${DSTAMP}.jar file -->
        <jar jarfile="dist/MyProject-${DSTAMP}.jar" basedir="${build}"/>
    </target>
</project>
```
- Update the scripts in the package.json file
```javascript
"scripts": {
  "watch": "webpack --watch -d --output ./target/classes/static/built/bundle.js",
          "webpack": "webpack",
          "build": "npm run webpack",
          "check": "echo Checking frontend",
          "clean": "echo Cleaning frontend",
          "lint": "echo Linting frontend",
          "test": "echo Testing frontend"
},
```

- Build the project and run the application
```javascript
ant build
ant run
```

- Configure plugin in the build.xml file
```javascript
<target name="frontend" description="Frontend build">
    <exec executable="npm" dir="." failonerror="true">
        <arg value="run"/>
        <arg value="build"/>
    </exec>
</target>
```
- Update the scripts in the package.json file
```javascript
"scripts": {
 "webpack": "webpack",
 "build": "npm run webpack",
 "check": "echo Checking frontend",
 "clean": "echo Cleaning frontend",
 "lint": "echo Linting frontend",
 "test": "echo Testing frontend"
 },
```

- Build the project and run the application
```javascript
ant build
ant run
```

- Add the following Ant tasks to the build.xml file
```javascript
<target name="copyJarToDist" description="Copy jar to dist folder">
    <copy todir="dist">
        <fileset dir="build/libs" includes="*.jar"/>
    </copy>
</target>

<target name="deleteWebpackFiles" description="Delete webpack files">
    <delete dir="src/main/resources/static/built"/>
</target>
```

- Run the application
```javascript
ant run
```


## Conclusion
In this assignment, I transitioned the basic version of the Tutorial application from Maven to Gradle.
I incorporated the frontend plugin into the build.gradle file and updated the scripts in the package.
json file. Additionally, I introduced Gradle tasks into the build.gradle file and successfully ran the application. 
The modifications were committed and merged into the main branch with the tag ca2-part2. 
As an alternative solution, I explored the use of Ant, which allowed me to compare the build process with Gradle. 
Both Gradle and Ant are powerful build automation tools, but they have significant differences. 
Gradle, with its flexibility and ability to handle large codebases, proved to be a robust choice for this project. 
Its integration with Groovy for build scripts and its ability to manage dependencies made the setup and execution of the project more efficient. 
On the other hand, Ant, being an older tool, takes a more configurative and less conventional approach. 
While it may be less powerful in terms of dependency management, Ant offers granular control over the build process, which can be beneficial in more complex scenarios. 
In summary, the choice between Gradle and Ant depends on the specific needs of the project. 
In this case, Gradle proved to be the more suitable choice due to its ease of use, powerful dependency management capabilities, and integration with IntelliJ IDEA. 
However, the experience with Ant was valuable in understanding the differences between the tools and how they can be applied in different contexts.










