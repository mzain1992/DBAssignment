package com.db.task.assignment.test;

import com.db.task.assignment.Application;
import com.db.task.assignment.model.Signals;
import com.db.task.assignment.service.Algo;
import com.db.task.assignment.service.SignalDataService;
import com.db.task.assignment.service.serviceImplv1.SignalHandlerImpl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
class ApplicationTest {


    @InjectMocks
    private SignalHandlerImpl application;

    @Mock
    private Algo algo;
    //
    @Mock
    SignalDataService signalDataService;


    @Test
    void testHandleSignalWithValidSignal() throws IOException {

        ObjectMapper map = new ObjectMapper();
        Signals signals = map.readValue(SignalHandlerImpl.class.getResourceAsStream("/signals.json"), Signals.class);
        when(signalDataService.getSignals()).thenReturn(signals);
        application.handleSignal(1);

        // Verify that the appropriate methods were called
        verify(algo, atLeast(1)).performCalc();
        verify(algo, atLeast(1)).setAlgoParam(1,60);
        verify(algo,never()).reverse();
        verify(algo,never()).cancelTrades();
        verify(algo, atLeast(1)).submitToMarket();


    }


    @Test
    void testHandleSignalWithValidSignal2() throws IOException {

        ObjectMapper map = new ObjectMapper();
        Signals signals = map.readValue(SignalHandlerImpl.class.getResourceAsStream("/signals.json"), Signals.class);
        when(signalDataService.getSignals()).thenReturn(signals);
        application.handleSignal(2);
        application.handleSignal(4);


        verify(algo,atLeast(1)).reverse();
        verify(algo,atLeast(1)).cancelTrades();



    }



}