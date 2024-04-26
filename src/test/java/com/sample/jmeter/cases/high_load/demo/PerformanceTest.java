package com.sample.jmeter.cases.high_load.demo;

import static us.abstracta.jmeter.javadsl.JmeterDsl.*;

import java.io.IOException;
import java.time.Duration;

import com.sample.jmeter.utils.ThroughputUtil;
import org.junit.jupiter.api.Test;

import com.sample.jmeter.constants.PathConst;
import com.sample.jmeter.thread_groups.Demo;

public class PerformanceTest {
    /* OUTPUT */
    private static final String REPORT_OUTPUT_PATH = PathConst.HIGH_LOAD_REPORTS_CONTEXT + "demo/report.csv";
    
    /* TEST SETTINGS*/
    int THREAD_PER_GROUPS = 2;
    int RAMP_UP_DURATION = 2;
    int HOLD_DURATION = 10;
    double RPS_PER_GROUP = 5;
    
    @Test
    void test() throws IOException {
        testPlan()
            .tearDownOnlyAfterMainThreadsDone()
            .children(
                httpCache().disable(),
                Demo.getThreadGroup("demo")
                    .rampToAndHold(THREAD_PER_GROUPS, Duration.ofSeconds(RAMP_UP_DURATION), Duration.ofSeconds(HOLD_DURATION - RAMP_UP_DURATION))
                    .children(
                        throughputTimer(ThroughputUtil.rpsToRpm(RPS_PER_GROUP))
                    ),
                jtlWriter("", REPORT_OUTPUT_PATH))
            .saveAsJmx(PathConst.HIGH_LOAD_CASES_CONTEXT + "demo/case.jmx");
    }
}
