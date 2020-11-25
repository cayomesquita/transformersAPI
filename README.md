# Transformers Battle API

This is a battle game API between the Autobots and Decepticons. This API conforms to Restful pattern and is built over a spring-boot application.

P.S.
* Java version: 1.8.0_241
* Apache Maven: 3.6.3 
* I will use curl(https://curl.se) in the examples. 

## Endpoints

### Transformers (path: /api/transformers)
#### Fetch all

Retrieve all transformer

**PATH** : `/api/transformers`

**Method** : `GET`

**URL** : `http://localhost:8080/api/transformers`

**Command**:

```shell script
curl http://localhost:8080/api/transformers
```

**Output**:
```json
[
  {
    "idTransformer": 1,
    "name": "Soundwave",
    "type": "D",
    "strength": 8,
    "intelligence": 9,
    "speed": 2,
    "endurance": 6,
    "rank": 7,
    "courage": 5,
    "firepower": 6,
    "skill": 10,
    "links": []
  },
...,
{
    "idTransformer": 6,
    "name": "HubcapR",
    "type": "D",
    "strength": 4,
    "intelligence": 4,
    "speed": 4,
    "endurance": 4,
    "rank": 4,
    "courage": 4,
    "firepower": 4,
    "skill": 4,
    "links": []
  }
]
```

#### Insert

Insert new transformer

**PATH** : `/api/transformers`

**Method** : `POST`

**URL** : `http://localhost:8080/api/transformers`

**Command**:

```shell script
curl -X POST -H "Content-Type: application/json" -d '{"name": "TransdormerZ","type": "A","strength": 2,"intelligenc": 2,"speed": 2,"endurance": 2,"rank": 2,"courage": 2,"firepower": 2}' http://localhost:8080/api/transformers
```

**Input**:

***Payload (json)***:
```json
{
  "name": "TransdormerZ",
  "type": "A",
  "strength": 2,
  "intelligence": 2,
  "speed": 2,
  "endurance": 2,
  "rank": 2,
  "courage": 2,
  "firepower": 2
}
```

#### Update

Update or insert new transformer

**PATH** : `/api/transformers/{TransformerId}`

**Method** : `PUT`

**URL** : `http://localhost:8080/api/transformers/9`

**Command**:

```shell script
curl -X PUT -H "Content-Type: application/json" -d '{"idTransformer": 4,"name": "Bluestreakqq","type": "A","strengt": 3,"intelligence": 3,"speed": 3,"endurance": 3,"rank": 3,"courage": 3,"firepower": 3,"skill": 3}' http://localhost:8080/api/transformers/4
```

**Input**:

***Payload (json)***:
```json
{
  "idTransformer": 4,
  "name": "Bluestreakqq",
  "type": "A",
  "strength": 3,
  "intelligence": 3,
  "speed": 3,
  "endurance": 3,
  "rank": 3,
  "courage": 3,
  "firepower": 3,
  "skill": 3
}
```

#### Delete

Delete transformer

**PATH** : `/api/transformers/{TransformerId}`

**Method** : `DELETE`

**URL** : `http://localhost:8080/api/transformers/4`

**Command**:

```shell script
curl -X DELETE http://localhost:8080/api/transformers/4
```

### Battle (path: /api/battles)
#### Battle Result

Get the battle result from a list of fighter (ids)

**PATH** : `/api/battles/result`

**Method** : `GET`

**URL** : `http://localhost:8080/api/battles/result`

**Command**:

```shell script
curl 'http://localhost:8080/api/battles/result?id=1&id=2&id=3'
```

**Input**

***Query Paramater***:
```
?id=1&id=2&id=3
```

**Output**
```json
{
  "numOfBattles": 1,
  "winningTeam": "Decepticons",
  "losingSurvivers": [
    "Hubcap"
  ],
  "links": []
}
```

## Build and Test API

The API is a maven project, so here are some commands to build and run the unit tests of the application:

### Clean and Build ###

```shell script
mvn clean compile
```

### Clean, Build and Test ###

```shell script
mvn clean test
```

### Clean, Build, Test and Package ###

```shell script
mvn clean package
```

## Run Application

### Run the application using spring-boot ###

```shell script
mvn clean spring-boot:run
```