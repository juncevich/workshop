package kafka.tutorial1;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class ProducerDemoKeys {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Logger logger = LoggerFactory.getLogger(ProducerDemoKeys.class);

        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

        for (int i = 0; i < 100; i++) {
            String topic = "second-topic";
            String value = "hello world " + i + " !";
            String key = "id_" + i;
            ProducerRecord<String, String> producerRecord = new ProducerRecord<>(topic, key, value);
            logger.info("Key: {}", key);
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
            }).get();
        }
        producer.flush();
        producer.close();

    }
}
