package com.sample.jmeter.thread_groups.components;

import com.sample.jmeter.utils.ThroughputUtil;
import us.abstracta.jmeter.javadsl.core.threadgroups.DslDefaultThreadGroup;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static us.abstracta.jmeter.javadsl.JmeterDsl.throughputTimer;

public class PhasedThreadGroupGenerator {
    public static List<DslDefaultThreadGroup> getPhasedThreadGroupList(
        ThreadGroupGeneratorIF generator,
        String threadGroupNamePrefix,
        List<Phase> phaseList
    ) {
        List<DslDefaultThreadGroup> threadGroupList = new ArrayList<>();
        
        int phaseNum = 0;
        for (Phase phase : phaseList) {
            threadGroupList.add(
                generator.getThreadGroup(threadGroupNamePrefix + "-" + phaseNum)
                    .rampToAndHold(
                        phase.getThreads(),
                        Duration.ofSeconds(phase.getRampUpDuration()),
                        Duration.ofSeconds(phase.getHoldDuration() - phase.getRampUpDuration())
                    ).children(
                        throughputTimer(ThroughputUtil.rpsToRpm(phase.getRps()))
                    )
            );
            phaseNum += 1;
        }
        return threadGroupList;
    }
}
