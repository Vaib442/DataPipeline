package FlinkIntegrationTests;

import dto.UserDto;
import flink.FlinkApplication;
import org.apache.flink.runtime.testutils.MiniClusterResourceConfiguration;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.sink.SinkFunction;
import org.apache.flink.test.util.MiniClusterWithClientResource;
import org.junit.ClassRule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class TestFlink {

    @ClassRule
    public static MiniClusterWithClientResource flinkCluster =
            new MiniClusterWithClientResource(
                    new MiniClusterResourceConfiguration.Builder()
                            .setNumberSlotsPerTaskManager(1)
                            .setNumberTaskManagers(1)
                            .build()

            );


    @Test
    public void TestFlinkPipeline() throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStreamSource<UserDto> dataStreamSource = env.fromCollection(Arrays.asList(

                new UserDto("110", "DEBIT", false),
                new UserDto("110", "DEBIT", true),
                new UserDto("110", "DEBIT", true),
                new UserDto("111", "DEBIT", false)
        ));

        CollectSink.values.clear();
        DataStream<UserDto> alertStream = FlinkApplication.getAlertStream(dataStreamSource);
        alertStream.print();
        alertStream.addSink(new CollectSink());
        env.execute("Testing Pipeline");
        assertTrue(CollectSink.values.contains("110"));

    }


    private static class CollectSink implements SinkFunction<UserDto> {

        public static final List<String> values = Collections.synchronizedList(new ArrayList<>());

        @Override
        public void invoke(UserDto value, Context context) throws Exception {
            values.add(value.getUserId());
        }
    }
}
