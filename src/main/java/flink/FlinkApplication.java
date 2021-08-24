package flink;

import constants.KafkaConstants;
import dto.UserDto;
import org.apache.flink.cep.CEP;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import java.util.List;

public class FlinkApplication {

    public static void main(String[] args) {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStreamSource<UserDto> dataStreamSource = env.addSource(FlinkProducerConsumer.createConsumer(KafkaConstants.TOPIC_NAME));
        DataStream<UserDto> alertStream = getAlertStream(dataStreamSource);
        alertStream.print();
        alertStream.addSink(FlinkProducerConsumer.createProducer(KafkaConstants.OUT_TOPIC_NAME));
        try {
            env.execute("Alert Job");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static DataStream<UserDto> getAlertStream(DataStreamSource<UserDto> dataStreamSource) {
        return CEP.pattern(
                dataStreamSource
                        .keyBy(UserDto::getUserId), PatternApi.validatePattern())
                .select(pattern -> {
                    List<UserDto> second = pattern.get("start");
                    return second.get(0);
                })
                .name("AlertStream");

    }
}


