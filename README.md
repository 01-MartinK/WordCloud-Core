# WordCloud-Core
Its a simple project which purpose is to generate a wordcloud. The purpose of this project is to test the microservice idea.
## Main component
This is the main component for the wordcloud system. It's what communicates with the worker via RabbitMQ and frontend that's made with React.

## Component Structure
### Core component - this repo
Handle communication between the worker and frontend. Receives request from frontend to parse file. Read file and sends the text via RabbitMQ to the worker. 
Receives request for certain wordlist. Gets the wordlist from the MySQL database that's running in the same system. Respons by sending the data back to the frontend. 

### [Worker Component](https://github.com/01-MartinK/WordCloud-Worker)
Receives wordcloud calculate request from the Core Component via RabbitMQ. The message request contains `{id, unparsed wordlist, excludedWords}`. 
It parses the data, modifies statuses and exports that data to the MySQL database.

### [Frontend Component](https://github.com/01-MartinK/WordCloud-Frontend)
The user interface where the user interacts with the programm. Enters a file, excluded words and sends it to the main component. Gets an access code for later retrieval.
Once inputted the access code the user receives the status and a wordcloud of words he used.

## Dependencies
Main dependencies initialized by [spring Initialz](https://start.spring.io/):
* Spring Web
* MySQL Driver
* H2
* JPA
* RabbitMQ

Extra dependencies:
* Gson
## Usage
The base Java version for this project is [Java19-Correto](https://github.com/corretto/corretto-19/releases).

git clone this repo `git clone https://github.com/01-MartinK/WordCloud-Core`.
After that run these commands in it's main root.
```
gradlew build

gradlew bootRun
```

If there is a need for quick easy docker setup.
This composer file is recommended. 
### Currently doesn't work.
```
version: "2"
services:
 mysql:
  container_name: mysql
  image: mysql:latest
  restart: unless-stopped
  ports:
   - "3306:3306"
  environment:
   MYSQL_ROOT_PASSWORD: "qwerty"
   MYSQL_DATABASE: "wordcloud"
   MYSQL_USER: "wordcloud"
   MYSQL_PASSWORD: "qwerty"
  volumes:
    - db:/var/lib/mysql
  networks:
   - static-network
 rabbitmq:
  image: rabbitmq:latest
  ports:
   - "5672"
  networks:
   - static-network
 frontend:
  build: https://github.com/01-MartinK/WordCloud-Frontend.git
  container_name: frontend-component
  networks:
   static-network:
    ipv4_address: 172.5.0.6
 core-component:
  build: https://github.com/01-MartinK/WordCloud-Core.git
  container_name: wordcloud-core
  depends_on:
   - mysql
   - rabbitmq
  ports:
   - "8080"
  networks:
   - static-network
 worker-component:
  build: https://github.com/01-MartinK/WordCloud-Worker.git
  container_name: wordcloud-worker
  depends_on:
   - mysql
   - rabbitmq
   - core-component
  ports:
   - "8080"
  networks:
   - static-network
volumes:
 db:
networks:
 static-network:
  driver: bridge
  ipam:
   config:
    - subnet: 172.5.0.0/16
      gateway: 172.5.0.1

```
