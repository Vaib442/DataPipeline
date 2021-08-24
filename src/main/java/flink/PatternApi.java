package flink;

import dto.UserDto;
import org.apache.flink.cep.pattern.Pattern;
import org.apache.flink.cep.pattern.conditions.IterativeCondition;
import org.apache.flink.streaming.api.windowing.time.Time;

public class PatternApi {

    public static Pattern<UserDto, UserDto> validatePattern() {
        return Pattern.<UserDto>begin("start")
                .where(new IterativeCondition<UserDto>() {
                    @Override
                    public boolean filter(UserDto userDto, Context<UserDto> context) throws Exception {
                        return userDto.isLocationChanged();
                    }
                })
                .times(2, 4)
                .within(Time.minutes(2));
    }

}
