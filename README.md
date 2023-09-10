# Spring Boot Trading Applicaiton DB Assignment
```
 mvn spring-boot:run
```

### STRUCTURE
We have a JSON file called 'signals.json' where we can easily append new signals by adding them to the file. Furthermore, we have the flexibility to quickly introduce new signals by specifying a new operation through API and subsequently incorporating it into the JSON file.
### Swagger UI
http://localhost:8090/swagger-ui.html

## End point :
#### GET /example/v1/signal/{signalId} (process the signal)
#### POST /example/v1/signal/{signalId} (add new Signal operation)

## Validation

Signals can be added when the operation is set to 'setAlgoParam,' in which case it is necessary to provide both a parameter and its corresponding value. Otherwise, no param should be provided.





