# backend-technical-test-v2

## Authors

- [LUCA SALETTA SILLERO](https://github.com/lsaletta)

## How to Use

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
