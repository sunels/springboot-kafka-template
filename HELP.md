# Basic Setup for this playground project

#How to install kafka && zookeper && conductor ui on ubuntu20 

Assuming you have install docker :)

docker network create kafka-net --driver bridge

docker run -d --name zookeeper-server -p 2181:2181 --network kafka-net -e ALLOW_ANONYMOUS_LOGIN=yes bitnami/zookeeper:latest

docker run -d --name kafka-serkans --network kafka-net -e ALLOW_PLAINTEXT_LISTENER=yes -e KAFKA_CFG_ZOOKEEPER_CONNECT=zookeeper-server:2181 -e KAFKA_CFG_ADVERTISED_LISTENERS=PLAINTEXT://localhost:9092 -p 9092:9092 bitnami/kafka:latest

#Postman Collection For test
https://www.getpostman.com/collections/61a50b6d0b729bb57b99

#I will use conductor ui for as kafka administrative tasks

before installing conductor on ubuntu 20 you need to install a missing library as mentioned in its docs

#See and install that missing dependency:
https://docs.conduktor.io/sign-in-section/install/linux#ubuntu-focal-20-missing-libffi6-when-installing-deb

Get the library from here : 
http://mirrors.kernel.org/ubuntu/pool/main/libf/libffi/libffi6_3.2.1-8_amd64.deb

sudo dpkg -i libffi6_3.2.1-8_amd64.deb

get the conductor : https://releases.conduktor.io/linux-deb

install it :
sudo dpkg -i conductor-xyz-version-X.deb

Create an account to use it connect to your local kafka cluster

You will be seeing something similar to : conductor.png

I have added a Postman Collection for testing the code : KafkaTest.postman_collection.json

It will be something like : postman.png

#How to connect to running docker kafka container ?
$ docker container exec -it kafka-serkans /bin/bash

Enjoy & Keep Learning !
