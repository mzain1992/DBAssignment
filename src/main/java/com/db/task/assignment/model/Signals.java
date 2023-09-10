package com.db.task.assignment.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

public class Signals {
    private Map<String, Signal> signals;

    public Map<String, Signal> getSignals() {
        return signals;
    }

    public void setSignals(Map<String, Signal> signals) {
        this.signals = signals;
    }
}
