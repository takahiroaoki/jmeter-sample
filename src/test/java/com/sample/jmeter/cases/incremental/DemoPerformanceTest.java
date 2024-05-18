package com.sample.jmeter.cases.incremental;

import static us.abstracta.jmeter.javadsl.JmeterDsl.*;

import java.io.IOException;
import java.util.List;

import com.sample.jmeter.thread_groups.components.Phase;
import com.sample.jmeter.thread_groups.components.PhasedThreadGroupGenerator;
import org.junit.jupiter.api.Test;

import com.sample.jmeter.constants.PathConst;
import com.sample.jmeter.thread_groups.Demo;
import us.abstracta.jmeter.javadsl.core.threadgroups.DslDefaultThreadGroup;

public class DemoPerformanceTest {
    /* OUTPUT */
    private static final String REPORT_OUTPUT_PATH = PathConst.INCREMENTAL_REPORTS_CONTEXT + "demo/report.csv";

    @Test
    void test() throws IOException {
        List<DslDefaultThreadGroup> threadGroupList = PhasedThreadGroupGenerator.getPhasedThreadGroupList(
                new Demo(),
                "demo",
                List.of(
                        new Phase(1.0, 1, 1, 20),
                        new Phase(1.0, 1, 1, 10)));

        testPlan()
                .tearDownOnlyAfterMainThreadsDone()
                .children(
                        httpCache().disable(),
                        httpCookies().disable(),
                        threadGroupList.get(0),
                        threadGroupList.get(1),
                        jtlWriter("", REPORT_OUTPUT_PATH))
                .saveAsJmx(PathConst.INCREMENTAL_CASES_CONTEXT + "demo/case.jmx");
    }
}