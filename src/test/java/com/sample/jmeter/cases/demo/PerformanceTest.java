package com.sample.jmeter.cases.demo;

import static us.abstracta.jmeter.javadsl.JmeterDsl.*;

import java.io.IOException;
import java.time.Duration;

import org.junit.jupiter.api.Test;

import com.sample.jmeter.constants.PathConst;
import com.sample.jmeter.thread_groups.Demo;

public class PerformanceTest {
    /* OUTPUT */
    private static final String REPORT_OUTPUT_PATH = PathConst.JMX_OUTPUT_CONTEXT + "demo/report.csv";

    @Test
    void test() throws IOException {
        testPlan()
                .tearDownOnlyAfterMainThreadsDone()
                .children(
                        httpCache().disable(),
                        Demo.getThreadGroup("demo", 2, Duration.ofSeconds(2L), Duration.ofSeconds(20L)),
                        jtlWriter("", REPORT_OUTPUT_PATH))
                .saveAsJmx(PathConst.JMX_OUTPUT_CONTEXT + "demo/case.jmx");
    }
}
