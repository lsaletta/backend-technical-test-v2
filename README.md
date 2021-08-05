# backend-technical-test-v2

## Authors

- [LUCA SALETTA SILLERO](https://github.com/lsaletta)

## How to Use

**IMPORTANT**: To run the application you need to have a Kafka server, you can configure the url from the application.yml or use the included kafka docker image.
```
cd kafka_config
docker-compose up -d
```
**TIP**: If you use the docker image you do not need to configure anything in the application.


Open terminal and execute:

```
git clone https://github.com/lsaletta/backend-technical-test-v2.git
cd backend-technical-test-v2
mvn clean install
java -jar target/backend-technical-test-2.0.0-SNAPSHOT.jar
```

**[Optional]** Generate docker image:

```
cd backend-technical-test-v2
mvn clean install docker:build 
docker run -p 8081:8081 backend-technical-test
```

**[Optional]** Run docker image:

```
docker run -p 8081:8081 backend-technical-test
```

## How use Api

Before follow previous step, API is running: 
`http://localhost:8688/tuimm/v1`

URL Swagger: http://localhost:8688/tuimm/v1/swagger-ui.html

### Available Endpoints

#### Client Operations
- GET http://localhost:8688/tuimm/v1/client: Get all clients.
- GET http://localhost:8688/tuimm/v1/client/{id}: Get client by id.
- POST http://localhost:8688/tuimm/v1/client: Create client.
- DELETE http://localhost:8688/tuimm/v1/client: Delete client.

#### Order Operations
- GET http://localhost:8688/tuimm/v1/search: Get orders [optional filter by client info].
- POST http://localhost:8688/tuimm/v1/search/{id}: Create order.
- PUT http://localhost:8688/tuimm/v1/client: Update order [only allows to update the following 5 minutes(*) after its creation].  
- DELETE http://localhost:8688/tuimm/v1/client: Delete order, only allows to delete the following 5 minutes after its creation.

#### Functional worflow
- When the user creates an order, it is persisted in the database and marked as PENDING.
- In the following 5 minutes the user is allowed to modify or delete the order.
  After this time, the order is eligible to be sent to the kitchen.
- The application has a job that runs every 30 seconds(**), retrieves PENDING orders with a creation time of more than 5 min.
  Next it marks them IN_PROGRESS and sends them to the kitchen, via a Kafka producer.
- The Kafka consumer will listen for messages sent and process them by marking them with the DONE status.

**IMPORTANT**: The application is loaded with a basic data set


**TIP**:
- (*)The update time is configurable by application properties.
- (**)The schedule of job is configurable by application properties.


## Potentials Improvements
- The development has been done directly in the 'master' branch of the repository, but the ideal would have been to divide the functionality into tasks and create branches for each of the tasks. eg: feature/clients_crud, feature/kafka_logic
- The kafka integration test does not work correctly, so it is marked as Disabled.
- It could be added to the docker compose of kafka the image of our application and thus avoid having to start kafka separately


