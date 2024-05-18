package com.sample.jmeter.thread_groups;

import static us.abstracta.jmeter.javadsl.JmeterDsl.*;

import com.sample.jmeter.constants.UrlConst;
import com.sample.jmeter.constants.PathConst;

import com.sample.jmeter.thread_groups.components.ThreadGroupGeneratorIF;
import us.abstracta.jmeter.javadsl.core.threadgroups.DslDefaultThreadGroup;

public class Demo implements ThreadGroupGeneratorIF {
	/* INPUT */
	private static final String PARAMS_DATA_PATH = PathConst.COMMON_DATA_CONTEXT + "demo/params.csv";

	public DslDefaultThreadGroup getThreadGroup(String threadGroupName) {
		return threadGroup(threadGroupName)
				.children(
						csvDataSet(PARAMS_DATA_PATH),
						httpSampler(UrlConst.WEB_TARGET + UrlConst.DEMO_ENDPOINT)
								.header("api-key", "${API_KEY}")
								.param("paramA", "${PARAM_A}")
								.param("paramB", "${PARAM_B}"),
						resultsTreeVisualizer());
	}
}