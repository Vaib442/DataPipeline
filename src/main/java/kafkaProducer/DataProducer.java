package kafkaProducer;

import constants.KafkaConstants;
import dto.UserDto;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class DataProducer {

    public static void main(String[] args) {

        try (KafkaProducer<String, UserDto> dataProducer = ProducerCreator.createProducer()) {
            String topic = KafkaConstants.TOPIC_NAME;
            String userId1 = "121";
            String userId2 = "122";

            UserDto record1 = new UserDto(userId1, "DEBIT", false);
            ProducerRecord<String, UserDto> producerRecord = new ProducerRecord<>(topic, userId1, record1);
            dataProducer.send(producerRecord);


            UserDto record2 = new UserDto(userId1, "DEBIT", true);
            ProducerRecord<String, UserDto> producerRecord2 = new ProducerRecord<>(topic, userId1, record2);
            dataProducer.send(producerRecord2);


            UserDto record3 = new UserDto(userId1, "DEBIT", true);
            ProducerRecord<String, UserDto> producerRecord3 = new ProducerRecord<>(topic, userId1, record3);
            dataProducer.send(producerRecord3);


            UserDto record4 = new UserDto(userId2, "DEBIT", false);
            ProducerRecord<String, UserDto> producerRecord4 = new ProducerRecord<>(topic, userId2, record4);
            dataProducer.send(producerRecord4);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
