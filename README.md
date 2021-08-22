# Tapsell Assignment

For information about this project, please refer to /docs/Task-Backend.pdf

## Modules

### DTO
This module share DTOs between projects. Contains 3 DTO:
- ImpressionEvent
- ClickEvent
- ElasticClickEvent

### Edge
This module exposes 2 APIs to get events and publish them on their Kafka topics.

Note: This module uses OpenAPI (swagger) to documents APIs;

Note: It uses port 8081.

### Consumer
This Module listens to Kafka topics and handles events (base on business on /docs/Task-Backend.pdf)

### Producer
This Module generates events and base on a probability generates click events too (currently I used 75% probability, but its API can accept new probability).

Note: This module uses OpenAPI (swagger) to documents APIs;

Note: It uses port 8083.

## Run on Local
### Initilazation envoiroment
Before runs each module, We need to provide these requirements:
- Kafka
- MongoDB
- Elasticsearch

For this purpose, I suggest using docker-compose files under the docker folder.

```Bash
docker-compose -f docker/{file-name}.yaml up -d
``` 

### Running
Each module can run with a simple command:
```Bash
gradle :{module-name}:bootRun
```

Note: DTO module is an exception, this module is only a library;

Note: I suggest running with this order: edge -> consumer -> producer

