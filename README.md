Google Books and ITunes Search API
===

API to get Books from Google Books and Albums from ITunes

## To Run

One of the following three methods can be used to run the Google Books and ITunes Search API.

1. Docker: To run the API via docker use the following command to pull the image and then run it (on port 8085) on container from Docker Hub.
```
    docker pull adnan5509/kramphub-books-albums-repo:latest
    docker run -p 8085:8083 adnan5509/kramphub-books-albums-repo:latest
```
2. Jar File: You can also run the project's jar file. Pull the jar file from this Google Drive link. 
```
    https://drive.google.com/drive/folders/1mQacqSRLqPRr6rRTad4xCLLpSpPdQcXb?usp=sharing
```
Use the following command to run it on port 8083.


```
    java -jar <PATH_TO_JAR_FILE>/BooksAndAlbums-0.0.1-SNAPSHOT.jar --server.port=8083
```
3. Source Code: You can pull the project source code from following GitHub link, and build and run it on your local machine.
```
    https://github.com/adnan5509/BooksAndAlbums
```
## Project Related Info:

Please read the following information related to the project.

* I am using port 8082 for default profile and port 8083 for prod profile.
* Currently, the active profile is set to prod.
* To request the search api via any api client (postman,browser etc.), use the following url. As I am using port 8083 for active profile prod, the url examples are with port 8083. It can be changed in docker run command, jar run command or by changing the server.port property in application.yml file.

    ```http://localhost:8083/api/BooksAndAlbums/getBooksAlbums/<YOUR_SEARCH_WORD>```
* You can also test the search api from Swagger UI by using the following url.

    ```http://localhost:8083/swagger-ui.html```
* For prod profile, I have exposed the following endpoints for the actuator.

      http://localhost:8083/swagger-ui.html

      http://localhost:8083/api-docs

      http://localhost:8083/actuator/health

      http://localhost:8083/actuator/prometheus
* Result limits are preconfigured to 5. It can be changed by updating the property apiCalls.maxResult.
* Google books api required an API key for making requests. For that I have created a new Google account and generated and API key which is added to the application.yml file with property name googleBooks.apiKey

## Technologies/Mechanisms used:
* Java 8 for development.
* Springboot 2.7 for autoconfiguration and embedded server.
* Maven for build.
* OpenApi for Self Documentation.
* FeignClient for calling third party services.
* Resilience4j circuit breaker pattern, bulkhead pattern for resilience.
* Micrometer, prometheus for exposing metrics on response times for upstream services.
* Docker for containerization.

## Improvement Suggestions:

Following improvements could have been made which I wasn’t able to do due to time limitations.

* Better exception handling using controller advice with appropriate response codes.
* Better visualization of metrics for response times by enabling a prometheus server on docker and using some metrics visualizing tool like Grafana.
* Using Asynchronous operations to introduce multithreading for concurrent requests.
* Unit tests to make repeated calls to third party services to check concurrent requests handling. 
