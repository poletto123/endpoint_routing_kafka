# EndPointRoutingKafka
An endpoint that routes JSON to H2 database or Kafka using Spring Boot

## Technologies that are used:

* Spring Boot 2.4.3
* Kafka 2.7.0 (Scala 2.13)
* Spring Data JPA
* Hibernate
* H2 Database
* Maven

## Instructions to run:

1. Download and unzip Kafka 2.7.0 (Scala 2.13)

2. Open the folder inside a terminal, and type the following command:

`bin/zookeeper-server-start.sh config/zookeeper.properties`

3. Open another terminal, and type the following command:

`bin/kafka-server-start.sh config/server.properties`

4. Either import the Spring Boot application in an IDE and run it, or open its folder in a terminal and run with the following Maven command:

`mvn spring-boot:run`

5. With both applications running, you can test the application by sending a POST request to `localhost:8080/sendMessage` in Postman with the following data:


`{
  "players": [
    {
      "name": "Sub zero",
      "type": "expert"
    },
    {
      "name": "Scorpion",
      "type": "novice"
    },
    {
      "name": "Reptile",
      "type": "meh"
    }
  ]
}`

Note: Kafka topic "novice-players" is created automatically by the class KafkaTopicConfig.java, which references application.properties

6. If "type" is "expert", Player is saved in H2 Database and a positive JSON message is returned to Postman. Here follows the credentials for H2 to inspect the data:

Browser URL: `http://localhost:8080/h2-console`
JDBC URL: `jdbc:h2:mem:testdb`
User name: `h2`
Password: `h2`

7. If "type" is "novice", Player is saved in a Kafka topic and a positive JSON message is returned to Postman. In a new terminal, go to the Kafka folder and write the following to inspect the data:

`bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic novice-players --from-beginning`

8. In case the "type" is neither "novice" or "expert", a negative JSON answer is returned to Postman.

