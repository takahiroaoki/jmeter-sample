package com.sample.jmeter.constants;

public class PathConst {
    public static final String CASES_CONTEXT = "cases/";
    
    public static final String COMMON_DATA_CONTEXT = CASES_CONTEXT + "common/";
    
    public static final String HIGH_LOAD_CASES_CONTEXT = CASES_CONTEXT + "high_load/";
    
    public static final String REPORTS_CONTEXT = "reports/";
    
    public static final String HIGH_LOAD_REPORTS_CONTEXT = REPORTS_CONTEXT + "high_load/";
    
    public static final String DATA_PATH_FORMATTER = "${__StringFromFile(%s)}";
}
