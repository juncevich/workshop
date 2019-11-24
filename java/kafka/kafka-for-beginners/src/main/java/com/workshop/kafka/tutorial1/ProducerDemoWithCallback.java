package com.workshop.kafka.tutorial1;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class ProducerDemoWithCallback {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(ProducerDemoWithCallback.class);

        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

        for (int i = 0; i< 1000000000; i++ ) {
            ProducerRecord<String, String> producerRecord = new ProducerRecord<>("first-topic", "hello world " + i + " !");

            producer.send(producerRecord, (metadata, exception) -> {
                if (exception == null) {
                    logger.info("Received new metadata.\n" +
                            "Topic: " + metadata.topic() + "\n"
                            + "Partition: " + metadata.partition() + "\n"
                            + "Offset: " + metadata.offset() + "\n"
                            + "Timestamp: " + metadata.timestamp());
                } else {
                    logger.error("Error while producing: ", exception);
                }
            });
        }
        producer.flush();
        producer.close();

    }
}
