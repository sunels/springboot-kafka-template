package com.example.dubara;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);

    @KafkaListener(topics = "${kafka.topicname}", containerFactory = "kafkaMessageKafkaListenerContainerFactory",
            id = "KafkaConsumer1", groupId = "mytestgroup-1",
            topicPartitions = { @TopicPartition(topic = "${kafka.topicname}", partitions = { "0"/*, "1"*/ })}
    )
    public void consumePartition0(KafkaMessage message) {
        LOGGER.info("Consumer-1 received payload='{}'", message.toString());
    }

    @KafkaListener(topics = "${kafka.topicname}", containerFactory = "kafkaMessageKafkaListenerContainerFactory",
            id = "KafkaConsumer2", groupId = "mytestgroup-1",
            topicPartitions = { @TopicPartition(topic = "${kafka.topicname}", partitions = { "1"/*, "1"*/ })}
    )
    public void consumePartition1(KafkaMessage message) {
        LOGGER.info("Consumer-2 received payload='{}'", message.toString());
    }

    @KafkaListener(topics = "${kafka.topicname}", containerFactory = "kafkaMessageKafkaListenerContainerFactory",
            id = "KafkaConsumer3", groupId = "mytestgroup-1",
            topicPartitions = {@TopicPartition(topic = "${kafka.topicname}", partitions = {"2"/*, "1"*/})}
    )
    public void consumePartition2(KafkaMessage message) {
        LOGGER.info("Consumer-3 received payload='{}'", message.toString());
    }

}