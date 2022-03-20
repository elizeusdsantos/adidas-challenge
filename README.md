# Adidas Chalenge
This app is splited in tree modules: mailing-service, public-service and subscription-service.
Every module represents a microservice, in the best scenario they would not be in the same parent
project but here lets do in that way only to make simple to keep it in GitHub (only one repository).

## Technologies
- Spring ecosystem
- Java17
- RabbitMQ
- Docker
- Docker Compose
- Swagger
- Google Code Style

## Project Estructure

adidas-challenge\
├── README.md\
├── build.gradle\
├── docker-compose.yml\
├── gradle\
│   └── wrapper\
├── gradlew\
├── gradlew.bat\
├── mailing-service\
│   ├── Dockerfile\
│   ├── build\
│   ├── build.gradle\
│   └── src\
├── public-service\
│   ├── Dockerfile\
│   ├── build\
│   ├── build.gradle\
│   └── src\
├── settings.gradle\
└── subscription\
├── Dockerfile\
├── build\
├── build.gradle\
└── src

<img src="_img/architecture.png" alt="architecture" width="600"/>

### public-service
this module represents the interface beetween the clients and the intern services. It's not secured
and only exposes the resources which are interesting to the clients, in this case one endpoint to
subscribe and one endpoint to cancel the subscription.

### subscription-service
This module represents all CRUD operations for a subscription. Delete and Create are called by
`public-service`to realize their operations.
After persists/update (in case of a customer resubscribe to the newsletter) a message is published
at a instance of `rabbitMQ` and consumed by `mailing-service`

This module utilizes spring-security to keep it safe, in this example we are using a simple
`basic in memory authentication` with fixed values. In a real case we would use JWT + OAuth2. 

### mailing-service
It's a console app which fakes a mail sending. It consumes messages from a queue in rabbitMQ.
No email is really sent, for this example only a message with a link to unsubscribe is shown in the
console.

## Running the project
It's all modules are contenereized and can be builded simply using the docker-compose file in the
root of the project.
If you have docker compose installed in your computer you can open your terminal inside the project folder and type `docker-compose up`.
I suggest you to do not use `-d` option, its interesting to see the logs, specially from the `mailing-service`.

Running from the first time can take a while, docker will download all images and after that all conteiners will download the dependencies and for sure it will take a few minutes.

After started, you can read the documentation made by swagger for the two apis using the following links:
(public-service) http://localhost:8080/v1/swagger-ui/index.html
(subscription-service) http://localhost:8081/v1/swagger-ui/index.html

I really recomend you to use a client like postman or insonia since you have to pass an autorization token with the headers.
You can see how to call the api using a basic auth in the picture(token `Basic YWRtaW46cGFzc3dvcmQ=`)

<img src="_img/postman_request_example.png" alt="architecture" width="600"/>
