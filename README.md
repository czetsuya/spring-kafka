[![ko-fi](https://www.ko-fi.com/img/githubbutton_sm.svg)](https://ko-fi.com/S6S0YXPX)

# Spring Kafka Demo
> Demonstrates Kafka sending (producer) and receiving (consumer) messages using Spring.

Repository Available at: git@github.com:czetsuya/spring-kafka.git

## Features
 - Kafka Producer
 - Kafka Consumer

## Technologies
 - Apache Kafka
 - Spring

# Development Setup
1. Download Spring STS
2. Import the GIT project, should contain 2 projects.

## Prerequisites
 - Apache Kafka

# Installation
 - Create the following Kafka topics
```sh
>bin\windows\kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic message
>bin\windows\kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 5 --topic partitioned
>bin\windows\kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic filtered
>bin\windows\kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic orders
```
 - List the topics
```sh
>bin\windows\kafka-topics.bat --list --zookeeper localhost:2181
```

## Producer
Sends messages to different topics namely:
 - message - string
 - partitioned - sends to partition 0 and 3
 - filtered
 - order - sends an object

## Consumer
 - message - receives any string message
 - partitioned - receives messages from partition 0 and 3
 - filtered - receives a message that does not contain 'echo'
 - order - receives an object
 
## Testing
1. Run zookeeper: bin\windows\zookeeper-server-start.bat config\zookeeper.properties.
2. Run kafka server: bin\windows\kafka-server-start.bat config\server.properties.
3. Run the consumer project. It will run on port 8002.
4. Run the producer project. Will use port 8001.

Here's the output of the consumer project:
```sh
2018-10-23 10:09:53.237 DEBUG Alexander --- [ntainer#3-0-C-1] c.b.k.KafkaMessageConsumerConfiguration  : received from group=beta, message=Alpha message
2018-10-23 10:09:53.237 DEBUG Alexander --- [ntainer#1-0-C-1] c.b.k.KafkaMessageConsumerConfiguration  : received from group=echo, message=Hello World!
2018-10-23 10:09:53.237 DEBUG Alexander --- [ntainer#5-0-C-1] c.b.k.KafkaMessageConsumerConfiguration  : received from group=alpha, message=Alpha message
2018-10-23 10:09:53.240 DEBUG Alexander --- [ntainer#0-0-C-1] c.b.k.KafkaMessageConsumerConfiguration  : received from group=charlie, message=Alpha message
2018-10-23 10:09:53.240 DEBUG Alexander --- [ntainer#4-0-C-1] c.b.k.KafkaMessageConsumerConfiguration  : received from group=delta, partition=0, message=Should be received in partitioned topic.
2018-10-23 10:09:53.243 DEBUG Alexander --- [ntainer#4-0-C-1] c.b.k.KafkaMessageConsumerConfiguration  : received from group=delta, partition=3, message=Should be received in partitioned topic.
2018-10-23 10:09:53.555 DEBUG Alexander --- [ntainer#2-0-C-1] c.b.k.KafkaMessageConsumerConfiguration  : received order=Order [id=1, product=Kafka Essentials, quantity=1, amount=100]
```

# Authors

* **Edward P. Legaspi** - *Architect* - [czetsuya](https://bitbucket.com/czetsuya)

# URLs
 * https://bitbucket.com/czetsuya

# Release History

 * 0.0.0.1-SNAPSHOT
    * Initial commit.
