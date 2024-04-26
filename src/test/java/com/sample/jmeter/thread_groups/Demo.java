package com.sample.jmeter.thread_groups;

import static us.abstracta.jmeter.javadsl.JmeterDsl.*;

import com.sample.jmeter.constants.UrlConst;
import com.sample.jmeter.constants.PathConst;

import com.sample.jmeter.thread_groups.components.ThreadGroupGeneratorIF;
import us.abstracta.jmeter.javadsl.core.threadgroups.DslDefaultThreadGroup;

public class Demo implements ThreadGroupGeneratorIF {
    /* INPUT */
    private static final String PROTOCOL_DATA_PATH = PathConst.COMMON_DATA_CONTEXT + "demo/protocol.txt";
    private static final String DOMAIN_DATA_PATH = PathConst.COMMON_DATA_CONTEXT + "demo/domain.txt";
    private static final String PORT_DATA_PATH = PathConst.COMMON_DATA_CONTEXT + "demo/port.txt";
    private static final String API_KEY_DATA_PATH = PathConst.COMMON_DATA_CONTEXT + "demo/api_key.txt";
    private static final String PARAMS_DATA_PATH = PathConst.COMMON_DATA_CONTEXT + "demo/params.csv";
    
    public DslDefaultThreadGroup getThreadGroup(String threadGroupName) {
        return threadGroup(threadGroupName)
            .children(
                vars()
                    .set("protocol", String.format(PathConst.DATA_PATH_FORMATTER, PROTOCOL_DATA_PATH))
                    .set("domain", String.format(PathConst.DATA_PATH_FORMATTER, DOMAIN_DATA_PATH))
                    .set("port", String.format(PathConst.DATA_PATH_FORMATTER, PORT_DATA_PATH))
                    .set("api-key", String.format(PathConst.DATA_PATH_FORMATTER, API_KEY_DATA_PATH)),
                csvDataSet(PARAMS_DATA_PATH),
                httpSampler("${protocol}://${domain}:${port}" + UrlConst.DEMO_ENDPOINT)
                    .header("api-key", "${api-key}")
                    .param("paramA", "${PARAM_A}")
                    .param("paramB", "${PARAM_B}"),
                resultsTreeVisualizer());
    }
}