package com.sample.jmeter.cases.demo;

import static us.abstracta.jmeter.javadsl.JmeterDsl.*;

import java.io.IOException;
import java.time.Duration;

import org.junit.jupiter.api.Test;

import com.sample.jmeter.constants.PathConst;
import com.sample.jmeter.constants.UrlConst;

public class PerformanceTest {
    /* INPUT */
    private static final String PROTOCOL_DATA_PATH = PathConst.JMX_OUTPUT_CONTEXT + "demo/data/protocol.txt";
    private static final String DOMAIN_DATA_PATH = PathConst.JMX_OUTPUT_CONTEXT + "demo/data/domain.txt";
    private static final String PORT_DATA_PATH = PathConst.JMX_OUTPUT_CONTEXT + "demo/data/port.txt";
    private static final String API_KEY_DATA_PATH = PathConst.JMX_OUTPUT_CONTEXT + "demo/data/api_key.txt";
    private static final String PARAMS_DATA_PATH = PathConst.JMX_OUTPUT_CONTEXT + "demo/data/params.csv";

    /* OUTPUT */
    private static final String REPORT_OUTPUT_PATH = PathConst.JMX_OUTPUT_CONTEXT + "demo/report.csv";

    @Test
    void test() throws IOException {
        testPlan(
                vars()
                        .set("protocol", String.format(PathConst.DATA_PATH_FORMATTER,
                                PROTOCOL_DATA_PATH))
                        .set("domain", String.format(PathConst.DATA_PATH_FORMATTER,
                                DOMAIN_DATA_PATH))
                        .set("port", String.format(PathConst.DATA_PATH_FORMATTER,
                                PORT_DATA_PATH))
                        .set("api-key", String.format(PathConst.DATA_PATH_FORMATTER,
                                API_KEY_DATA_PATH)),
                csvDataSet(PARAMS_DATA_PATH))
                .tearDownOnlyAfterMainThreadsDone()
                .children(
                        httpCache().disable(),
                        threadGroup("ThreadGroup0")
                                .rampToAndHold(5, Duration.ofSeconds(5),
                                        Duration.ofSeconds(10))
                                .children(
                                        throughputTimer(60.0),
                                        httpSampler("${protocol}://${domain}:${port}"
                                                + UrlConst.DEMO_ENDPOINT)
                                                .header("api-key",
                                                        "${api-key}")
                                                .param("paramA", "${PARAM_A}")
                                                .param("paramB", "${PARAM_B}"),
                                        resultsTreeVisualizer()),
                        jtlWriter("", REPORT_OUTPUT_PATH))
                .saveAsJmx(PathConst.JMX_OUTPUT_CONTEXT + "demo/case.jmx");
    }
}
