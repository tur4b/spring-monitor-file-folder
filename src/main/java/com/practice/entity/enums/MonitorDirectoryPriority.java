package com.practice.entity.enums;

public enum MonitorDirectoryPriority {

    INFO(0, "informational"), LOW(1, "low"), MODERATE(2, "moderate"), HIGH(3, "high"), CRITICAL(4, "critical");

    private int priority;
    private String name;

    MonitorDirectoryPriority(int priority, String name) {
        this.priority = priority;
        this.name = name;
    }

    public int getPriority() {
        return this.priority;
    }

    public String getName() {
        return this.name;
    }

}