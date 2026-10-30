package com.himanshudhingra.util;

public class DebugLogger {
    private final String logTag;

    public DebugLogger(String logTag) {
        this.logTag = logTag;
    }
    public void log(String msg) {
        System.out.println(logTag + ": " + msg);
    }
}
