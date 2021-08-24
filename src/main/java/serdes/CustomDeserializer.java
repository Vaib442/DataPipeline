package serdes;

import dto.UserDto;
import org.apache.flink.api.common.serialization.DeserializationSchema;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;
import java.util.Map;

public class CustomDeserializer implements Deserializer<UserDto>, DeserializationSchema<UserDto> {

    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure(Map<String, ?> map, boolean b) {

    }

    @Override
    public UserDto deserialize(String s, byte[] bytes) {
        try {
            return objectMapper.readValue(bytes, UserDto.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new UserDto();
    }

    @Override
    public void close() {

    }

    @Override
    public UserDto deserialize(byte[] bytes) throws IOException {
        return objectMapper.readValue(bytes, UserDto.class);
    }

    @Override
    public boolean isEndOfStream(UserDto userDto) {
        return false;
    }


    @Override
    public TypeInformation<UserDto> getProducedType() {
        return TypeInformation.of(UserDto.class);
    }
}
