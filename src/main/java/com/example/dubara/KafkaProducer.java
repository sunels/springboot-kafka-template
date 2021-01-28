package com.example.dubara;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/kafka")
public class KafkaProducer {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducer.class);

    @Autowired
    private KafkaTemplate<String, String> stringKafkaTemplate;

    @Autowired
    private KafkaTemplate<String, Object> objectKafkaTemplate;

    @GetMapping(name = "getAllByAccountNumber", value = "/topic/{topic}/payload/{payload}", produces = "application/json")
    public void send(@PathVariable String topic, @PathVariable String payload) {
        ListenableFuture<SendResult<String, String>> future = stringKafkaTemplate.send(topic, payload.substring(4), payload);

        future.addCallback(new ListenableFutureCallback<>() {

            @Override
            public void onSuccess(SendResult<String, String> result) {
                System.out.println("Sent message=[" + payload +
                        "] with offset=[" + result.getRecordMetadata().offset() + "]");
            }

            @Override
            public void onFailure(Throwable ex) {
                System.out.println("Unable to send message=["
                        + payload + "] due to : " + ex.getMessage());
            }
        });
    }

    @PostMapping(name = "createNew", value = "/topic/{topic}", produces = "application/json")
    public ResponseEntity<KafkaMessage> createNew(@PathVariable String topic, @RequestBody KafkaMessage kafkaMessage) {
        ListenableFuture<SendResult<String, Object>> future = objectKafkaTemplate.send(topic,kafkaMessage.getId().toString(),kafkaMessage);

        future.addCallback(new ListenableFutureCallback<>() {

            @Override
            public void onSuccess(SendResult<String, Object> result) {
                System.out.println("Sent message=[" + kafkaMessage +
                        "] with offset=[" + result.getRecordMetadata().offset() + "]");
            }

            @Override
            public void onFailure(Throwable ex) {
                System.out.println("Unable to send message=["
                        + kafkaMessage + "] due to : " + ex.getMessage());
            }
        });
        return null;
    }

}
