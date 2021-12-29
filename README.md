#Introduction

#Non-functional requirements

* JDK 16+;<br/>
* Maven 3.6+;<br/>
* Database PostgreSQL.<br/>
* Docker-machine.<br/>

#Functional requirements

#Use case

#User guide

###Compile project and create container

$ git clone https://github.com/Hardziyevich/application_tomcat </br>
$ cd ./application</br>
$ mvn clean package</br>
$ docker build -t war -f docker/Dockerfile . </br>
$ cd ./docker-compose </br>
$ docker compose up </br>

curl example for request:

Search: curl -X GET </br>
