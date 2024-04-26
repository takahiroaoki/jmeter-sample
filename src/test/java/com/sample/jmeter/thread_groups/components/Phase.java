package com.sample.jmeter.thread_groups.components;

public class Phase {
    private final double rps;
    
    private final int threads;
    
    private final long rampUpDuration;
    
    private final long holdDuration;
    
    public Phase(
        double rps,
        int threads,
        long rampUpDuration,
        long holdDuration
    ) {
        this.rps = rps;
        this.threads = threads;
        this.rampUpDuration = rampUpDuration;
        this.holdDuration = holdDuration;
    }
    
    public double getRps() {
        return rps;
    }
    
    public int getThreads() {
        return threads;
    }
    
    public long getRampUpDuration() {
        return rampUpDuration;
    }
    
    public long getHoldDuration() {
        return holdDuration;
    }
}
