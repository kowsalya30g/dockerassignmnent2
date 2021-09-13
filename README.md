DOCKER ASSINGMENT

# DOCKER ASSINGMENT to dockerize a spring boot project 

Before this just keep pulling the mysql image using the command 
````
docker pull mysql:5.7
````
### Step 1: Maven install and package 

Go inside the spring boot project and give commands like the below mentioned for creating a jar file 

eg:
````
maven clean 
maven package -DskipTests 
````
Note: u can see the jar file built in the target classes and give that jar file name in docker file

### Step 2: Create a Dockerfile(dockerfile)

example:-
````
FROM openjdk:8-jre-alpine
ADD target/employee-0.0.1-SNAPSHOT.jar employee-0.0.1-SNAPSHOT.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "employee-0.0.1-SNAPSHOT.jar"]
````
### Step 3: Make change to application.properties in springboot steps to change application properties 

````
server: 
  servlet:
    context-path: /demo
spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:mysql://mysql-docker:3306/demo
spring.datasource.username=root
spring.datasource.password=root123
server.port=8082
jpa:
    properties:
      hibernate:
        dialect: org.hibernate.dialect.MySQL5InnoDBDialect
        ddl-auto: update
````

Note : instead of localhost:3306 mention the docker container name 

### Step 4: Build the docker file
````
docker build -t spring-docker .
````
U can give any name for ur image it will build in that name 

### Step 5 : Docker compose file 
Once two images got built now using Docker compose file we are gonna run that two image 
Create a file docker-compose.yml
````
version: '3'
services:
  mysql-docker:
    image: 'mysql:5.7'
    environment:
      - MYSQL_ROOT_PASSWORD=root123
      - MYSQL_PASSWORD=root123
      - MYSQL_DATABASE=demo
    ports:
      - "3307:3306"
  springboot-docker-container:
    image: spring-docker
    ports:
      - "8082:8082"
    environment:
      SPRING_DATASOURCE_URL: jdbc:mysql://mysql-docker:3306/demo?autoReconnect=true&useSSL=false
      SPRING_DATASOURCE_USERNAME: "root"
      SPRING_DATASOURCE_PASSWORD: "root123"
    build:
      context: "./"
      dockerfile: "dockerfile"
    depends_on:
      - mysql-docker

````
Note: mention the two image names and mention your environment respectively

### Step 6 : Run the docker compose file 

````
docker-compose -f docker-compose.yml up
````
or 
````
docker compose up 

````

In the output we can see that jvm started application and tomcat server started at port 8082 

### Step 7 : Open the containers and run it 

When springboot container opened in browser , 
give as 
````
http://localhost/getall

````
as u mentioned in ur spring controller project give that pathvariables and see the output 
the postman output can be shown here 

Next step --> go to mysql workbench 
Add connection 
Give the hostname and port respective to the things running on mysql container image 
eg:
````
Hostname : localhost Port : 3307

````
Type the password and username u mentioned in docker compose file 

Then inside the sql workbench the created database shown 

give commands like :
````
use demo;   
select*from employee_details;

````
demo--> DB name 
employee_details: table name
