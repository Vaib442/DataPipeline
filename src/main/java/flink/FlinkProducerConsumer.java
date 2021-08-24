package flink;

import constants.KafkaConstants;
import dto.UserDto;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import serdes.CustomDeserializer;
import serdes.CustomSerializer;

import java.util.Properties;

public class FlinkProducerConsumer {
    public static FlinkKafkaConsumer<UserDto> createConsumer(String topic) {
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", KafkaConstants.KAFKA_BROKERS);
        properties.setProperty("group.id", "test");
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
        return new FlinkKafkaConsumer<>(topic, new CustomDeserializer(), properties);
    }

    public static FlinkKafkaProducer<UserDto> createProducer(String topic){
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", KafkaConstants.KAFKA_BROKERS);
        return new FlinkKafkaProducer<>(topic, new CustomSerializer(),properties, FlinkKafkaProducer.Semantic.EXACTLY_ONCE);
    }
}
