# CA1 - Version Control Project

## Table of Contents
* [Introduction](#Introduction)
* [Setup](#Setup)
    * [Clone the Repository](#Clone-the-Repository)
    * [Create Folder CA1](#Create-Folder-CA1)
    * [Navigate to the Project Directory](#Navigate-to-the-Project-Directory)
* [Exercise Part 1](#Exercise-Part-1)
    * [Copy Tutorial Code](#Copy-Tutorial-Code)
    * [Commit and Push](#Commit-and-Push)
    * [Tag Initial Version](#Tag-Initial-Version)
    * [Develop and Test New Feature](#Develop-and-Test-New-Feature)
    * [Commit and Tag New Version](#Commit-and-Tag-New-Version)
    * [Mark Repository with ca1-part1](#Mark-Repository-with-ca1-part1)
* [Exercise Part 2](#Exercise-Part-2)
    * [Publish Stable Versions](#Publish-Stable-Versions)
    * [Back to Main Branch](#Back-to-Main-Branch)
        * [Develop New Features](#Develop-New-Features)
            * [Create New Branch - Add Email Field](#Create-New-Branch---Add-Email-Field)
        * [Back to Main Branch](#Back-to-Main-Branch)
            * [Create New Branch - Fix Invalid Email](#Create-New-Branch---Fix-Invalid-Email)
        * [Back to Main Branch](#Back-to-Main-Branch)
    * [Merge and Tag New Versions](#Merge-and-Tag-New-Versions)
    * [Mark Repository with ca1-part2](#Mark-Repository-with-ca1-part2)
* [Alternative Version Control Solution](#Alternative-Version-Control-Solution)
    * [Project Development with Mercurial](#Project-Development-with-Mercurial)
    * [Comparison Mercurial with Git](#Comparison-Mercurial-with-Git)
    * [Mercurial Design of the Solution](#Mercurial-Design-of-the-Solution)
    * [Project Development with Bazaar](#Project-Development-with-Bazaar)
    * [Comparison Bazaar with Git](#Comparison-Bazaar-with-Git)
* [Conclusion](#Conclusion)

## Introduction
This repository contains the code for a React.js and Spring Data REST application.
The project is structured to follow a specific workflow using Git for version control.
Additionally, an alternative version control solution is explored at the end of the assignment.

## Setup
### Clone the Repository

```javascript
git clone https://github.com/ASofiaRocha/DeVops-23-24-PSM-1231867.git
```

### Create Folder CA1
```javascript
mkdir CA1
```
### Navigate to the Project Directory
```javascript
cd CA1
```

## Exercise Part 1
### Copy Tutorial Code - Tutorial React.js and Spring Data REST

```javascript
cp -r C:\Users\Public\Prof\tut-react-and-spring-data-rest\basic CA1
```
### Commit and Push
```javascript
git add .
git commit -m "Adicionei código inicial do Tutorial React.js e Spring Data REST Application"

git push
```

### Tag Initial Version
```javascript
git tag v1.1.0
git push --tags
```

### Develop and Test New Feature - Add jobYears Field
```javascript
git add .
git commit -m "Adicionei suporte para o novo campo 'jobYears' e testes unitários"

```

### Commit and Tag New Version
```javascript
git push origin main
git tag v1.2.0
git push --tags
```

### Mark Repository with ca1-part1
```javascript
git tag ca1-part1
git push --tags
```

## Exercise Part 2

### Publish Stable Versions
### Back to main branch
```javascript
//  Use main branch to publish stable versions
git checkout main
```

### Creat new branch - Add Email Field
```javascript
git checkout -b email-field
// Add email field, write unit tests, and debug
git add .
git commit -m "Adicionei campo de email e testes"
git push origin email-field  
```
### Merge and Tag New Versions
```javascript
// Merge email-field branch to main
git merge --no-ff email-field
git tag v1.3.0
git push --tags
```

### Back to main branch
```javascript
git checkout main
```

### Create new branch - Fix Invalid Email
```javascript
git checkout -b fix-invalid-email
// Ensure valid email format, debug, and write tests
git add .
git commit -m "Correção da  validação de email"
git push origin fix-invalid-email
```
### Back to main branch
```javascript
git checkout main
```

### Merge and Tag New Versions
```javascript
 git merge --no-ff fix-invalid-email
git tag v1.3.1
```

### Mark Repository with ca1-part2
```javascript
git tag ca1-part2
git commit -m "Finalizando parte 2 do projeto. Closes #1, Closes #2, Closes #3"
git push --tags
```

## Alternative Version Control Solution

#### Project Development with Mercurial
##### Part 1
```javascript
hg clone <repository_url>
mkdir CA1
cd CA1
cp -r <tutorial_code_directory> CA1
hg add
hg commit -m "Copy tutorial code"
hg push
hg tag v1.1.0
hg push --tags
hg branch new-feature
hg commit -m "Add new feature"
hg push
hg tag v1.2.0
hg push --tags
hg tag ca1-part1
hg push --tags
```
##### Part 2
```javascript
hg branch email-field
hg commit -m "Add email field, unit tests, and debug"
hg branch fix-invalid-email
hg commit -m "Fix invalid email, debug, and add tests"
hg update default
hg merge email-field
hg commit -m "Merge email feature back to default"
hg tag v1.3.0
hg push --tags
hg tag ca1-part2
hg push --tags
```

### Comparison Mercurial with Git
Mercurial shares many similarities with Git in terms of version control features.
Both tools are distributed, offer efficient change tracking, and support branching workflows.

### Mercurial Design of the Solution
To achieve the same goals presented in this exercise:
###### Using Branches:
- Mercurial uses branches similarly to Git. Commands like hg branch, hg update, and hg commit are used to create, switch, and commit changes to branches.
###### Stable Versions:
- Stable versions can be maintained on the default branch, and new features can be developed on separate branches, followed by merging into the default branch.
###### Tags:
- Mercurial supports tags to mark specific versions. Use hg tag to create tags and hg push --tags to send them to the remote repository.
###### Comparative Analysis:
- While differences exist, most commands and concepts are similar between Git and Mercurial. Both tools provide robust solutions for distributed version control.


#### Project Development with Bazaar
##### Part 1
```javascript
bzr init-repo <repository_url>
bzr branch <repository_url> CA1
cd CA1
cp -r <tutorial_code_directory> CA1
bzr add
bzr commit -m "Copy tutorial code"
bzr push
bzr tag v1.1.0
bzr push --tags
bzr switch -b new-feature
bzr commit -m "Add new feature"
bzr push
bzr tag v1.2.0
bzr push --tags
bzr tag ca1-part1
bzr push --tags
```

##### Part 2
```javascript
bzr switch -b email-field
bzr commit -m "Add email field, unit tests, and debug"
bzr switch -b fix-invalid-email
bzr commit -m "Fix invalid email, debug, and add tests"
bzr switch master
bzr merge email-field
bzr commit -m "Merge email feature back to master"
bzr tag v1.3.0
bzr push --tags
bzr tag ca1-part2
bzr push --tags
```
### Comparison Bazaar with Git
Bazaar offers functionalities similar to Git in terms of distributed version control.
Both tools share many similarities in terms of core features.

### Bazaar Design of the Solution
Bazaar uses branches, tags, and commands similar to Git.
The main differences lie in the syntax of the commands, but the fundamental concepts remain the same.

### Conclusion
Bazaar and Mercurial are both viable alternatives to Git for version control.
While Git is the most widely used tool, Bazaar and Mercurial offer similar features and can be used to achieve the same goals.


