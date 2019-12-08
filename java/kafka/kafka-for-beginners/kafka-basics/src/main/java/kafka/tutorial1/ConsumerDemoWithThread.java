package kafka.tutorial1;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;


public class ConsumerDemoWithThread {
    public static void main(String[] args) {


        new ConsumerDemoWithThread().run();
    }

    private ConsumerDemoWithThread() {

    }

    private void run() {

        Logger logger = LoggerFactory.getLogger(ConsumerDemoWithThread.class);

        CountDownLatch latch = new CountDownLatch(1);
        logger.info("Creating the consumer thread.");
        ConsumerRunnable consumerRunnable = new ConsumerRunnable("localhost:9092",
                "application_group_1",
                "second-topic",
                latch
        );

        Thread consumerThread = new Thread(consumerRunnable);
        consumerThread.start();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            logger.info("Caught shutdown hook");
            consumerRunnable.shutdown();
        }
        ));

        try {
            latch.await();
        } catch (InterruptedException e) {
            logger.error("Application got interrupted", e);
        } finally {
            logger.info("Application is closing.");
        }
    }

    public class ConsumerRunnable implements Runnable {
        private CountDownLatch latch;
        private KafkaConsumer<String, String> consumer;
        private Logger logger = LoggerFactory.getLogger(ConsumerRunnable.class);

        public ConsumerRunnable(
                String bootstrapServers,
                String groupId,
                String topic,
                CountDownLatch latch) {
            this.latch = latch;

            Properties properties = new Properties();
            properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);//localhost:9092
            properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
            properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
            properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, groupId);//application_group_1
            properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");//earliest
            consumer = new KafkaConsumer<>(properties);
            consumer.subscribe(Collections.singletonList(topic));//second-topic
        }

        @Override
        public void run() {
            try {
                while (true) {
                    ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
                    records.forEach(
                            r -> logger.info("Key: " + r.key() + ", Value: " + r.value() + "\n"
                                    + "Partition: " + r.partition() + ", Offset: " + r.offset())
                    );
                }
            } catch (WakeupException e) {
                logger.info("Received shutdown signal !");
            } finally {
                consumer.close();
                latch.countDown();
            }
        }

        public void shutdown() {
            consumer.wakeup();
        }
    }
}
