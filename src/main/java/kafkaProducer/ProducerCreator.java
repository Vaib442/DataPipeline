package kafkaProducer;

import constants.KafkaConstants;
import dto.UserDto;
import org.apache.kafka.clients.producer.KafkaProducer;

import java.util.Properties;

public class ProducerCreator {
    public static KafkaProducer<String, UserDto> createProducer() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", KafkaConstants.KAFKA_BROKERS);
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "serdes.CustomSerializer");
        properties.put("acks", "all");
        properties.put("enable.idempotence", "true");
        return new KafkaProducer<>(properties);
    }
}

