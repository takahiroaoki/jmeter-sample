package com.sample.jmeter.thread_groups;

import us.abstracta.jmeter.javadsl.core.threadgroups.DslDefaultThreadGroup;

public interface ThreadGroupGeneratorIF {
    DslDefaultThreadGroup getThreadGroup(String threadGroupName);
}
