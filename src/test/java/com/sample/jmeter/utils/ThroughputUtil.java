package com.sample.jmeter.utils;

public class ThroughputUtil {
    public static double rpsToRpm(double rps) {
        return rps * 60.0;
    }
}
