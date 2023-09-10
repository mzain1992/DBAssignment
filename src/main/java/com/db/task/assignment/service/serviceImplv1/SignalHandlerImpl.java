package com.db.task.assignment.service.serviceImplv1;

import com.db.task.assignment.model.Action;
import com.db.task.assignment.model.Signal;
import com.db.task.assignment.service.Algo;
import com.db.task.assignment.service.SignalDataService;
import com.db.task.assignment.service.SignalHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.logging.Level;
import java.util.logging.Logger;


@Component
public class SignalHandlerImpl implements SignalHandler {
    Algo algo = new Algo();
    private final Logger logger = Logger.getLogger(SignalHandlerImpl.class.getName());

    @Autowired
    SignalDataService signalDataService;


    public void handleSignal(int signal) {
        Signal signalData = signalDataService.getSignals().getSignals().get(Integer.toString(signal));
        processSignalConfiguration(signalData);
        algo.doAlgo();

    }
    private void processSignalConfiguration(Signal signal) {

        for (Action action : signal.getActions()) {
            String operation = action.getOperation();
            logger.log(Level.INFO, "Processing operation: " + operation);
            switch (operation) {
                case "setUp":
                    algo.setUp();
                    break;
                case "setAlgoParam":
                    Integer param = action.getParam();
                    Integer value = action.getValue();
                    algo.setAlgoParam(param, value);
                    logger.log(Level.INFO, "Setting parameter " + param + " to " + value);
                    break;
                case "reverse":
                    algo.reverse();
                    break;
                case "calc":
                    algo.performCalc();
                    break;
                case "submit":
                    algo.submitToMarket();
                    break;
                case "cancel":
                    algo.cancelTrades();
                    break;
                default:
                    logger.log(Level.WARNING, "Unknown operation: " + operation);
                    // Handle unknown operation
                    break;
            }

        }
    }
}