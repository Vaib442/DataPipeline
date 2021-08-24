package serdes;

import constants.KafkaConstants;
import dto.UserDto;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.flink.streaming.connectors.kafka.KafkaSerializationSchema;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class CustomSerializer implements Serializer<UserDto>, KafkaSerializationSchema<UserDto> {
    @Override
    public void configure(Map<String, ?> map, boolean b) {
    }

    @Override
    public byte[] serialize(String s, UserDto userDto) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(userDto).getBytes();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    @Override
    public void close() {

    }

    @Override
    public ProducerRecord<byte[], byte[]> serialize(UserDto userDto, Long aLong) {
        ProducerRecord<byte[],byte[]> producerRecord = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            producerRecord = new ProducerRecord<>(KafkaConstants.OUT_TOPIC_NAME, userDto.getUserId().getBytes(),
                    objectMapper.writeValueAsString(userDto).getBytes());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return producerRecord;
    }
}
