package com.sample.jmeter.thread_groups;

import static us.abstracta.jmeter.javadsl.JmeterDsl.*;

import java.time.Duration;

import com.sample.jmeter.constants.UrlConst;
import com.sample.jmeter.constants.PathConst;

import us.abstracta.jmeter.javadsl.core.threadgroups.DslDefaultThreadGroup;

public class Demo {
    /* INPUT */
    private static final String PROTOCOL_DATA_PATH = PathConst.JMX_OUTPUT_CONTEXT + "demo/data/protocol.txt";
    private static final String DOMAIN_DATA_PATH = PathConst.JMX_OUTPUT_CONTEXT + "demo/data/domain.txt";
    private static final String PORT_DATA_PATH = PathConst.JMX_OUTPUT_CONTEXT + "demo/data/port.txt";
    private static final String API_KEY_DATA_PATH = PathConst.JMX_OUTPUT_CONTEXT + "demo/data/api_key.txt";
    private static final String PARAMS_DATA_PATH = PathConst.JMX_OUTPUT_CONTEXT + "demo/data/params.csv";

    public static DslDefaultThreadGroup getThreadGroup(
            String threadGroupName,
            int threadsPerGroup,
            Duration rampUpDuration,
            Duration holdDuration) {
        return threadGroup(threadGroupName).rampToAndHold(threadsPerGroup, rampUpDuration, holdDuration)
                .children(
                        vars()
                                .set("protocol", String.format(PathConst.DATA_PATH_FORMATTER, PROTOCOL_DATA_PATH))
                                .set("domain", String.format(PathConst.DATA_PATH_FORMATTER, DOMAIN_DATA_PATH))
                                .set("port", String.format(PathConst.DATA_PATH_FORMATTER, PORT_DATA_PATH))
                                .set("api-key", String.format(PathConst.DATA_PATH_FORMATTER, API_KEY_DATA_PATH)),
                        csvDataSet(PARAMS_DATA_PATH),
                        throughputTimer(60.0),
                        httpSampler("${protocol}://${domain}:${port}" + UrlConst.DEMO_ENDPOINT)
                                .header("api-key", "${api-key}")
                                .param("paramA", "${PARAM_A}")
                                .param("paramB", "${PARAM_B}"),
                        resultsTreeVisualizer());
    }
}