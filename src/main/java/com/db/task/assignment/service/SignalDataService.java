package com.db.task.assignment.service;

import com.db.task.assignment.model.Signal;
import com.db.task.assignment.model.Signals;
import com.db.task.assignment.service.serviceImplv1.SignalHandlerImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.*;

@Component
public class SignalDataService {

    private Signals signals;

    @Autowired
    ObjectMapper objectMapper;

    public Signals getSignals() {
        return signals;
    }

    public void setSignals(Signals signals) {
        this.signals = signals;
    }

    @PostConstruct
    public void readSignalFromFie() throws IOException {
        //   ObjectMapper objectMapper = new ObjectMapper();
        signals = objectMapper.readValue(SignalHandlerImpl.class.getResourceAsStream("/signals.json"), Signals.class);

    }

    public void add(String num, Signal signal) throws IOException {
        // ObjectMapper objectMapper = new ObjectMapper();
        signals.getSignals().put(num, signal);
    }
}
