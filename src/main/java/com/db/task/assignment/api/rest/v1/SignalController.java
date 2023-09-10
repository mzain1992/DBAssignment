package com.db.task.assignment.api.rest.v1;

import com.db.task.assignment.api.rest.AbstractRestHandler;
import com.db.task.assignment.model.validation.ModelValidation;
import com.db.task.assignment.model.Signal;
import com.db.task.assignment.service.SignalDataService;
import com.db.task.assignment.service.SignalHandler;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping(value = "/tradingApplication/v1/signal")
@Api(tags = {"signal"})
public class SignalController extends AbstractRestHandler {

    @Autowired
    private SignalHandler signalHandler;

    @Autowired
    ModelValidation modelValidation;

    @Autowired
    SignalDataService signalDataService;


    @RequestMapping(value = "/{signalId}",
            method = RequestMethod.GET,
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "process a signal Id.", notes = "You have to provide a valid signal Id.")
    @ResponseBody
    ResponseEntity<Object> requestSignal(@ApiParam(value = "signal Id that need to process.", required = true)
                                         @PathVariable("signalId") Integer id,
                                         HttpServletRequest request, HttpServletResponse response) throws Exception {
        signalHandler.handleSignal(id);
        return ResponseEntity.status(HttpStatus.OK).build();

    }


    @RequestMapping(value = "/{signalId}",
            method = RequestMethod.POST,
            consumes = {"application/json"},
            produces = {"application/json"})
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create a new signal configuration.", notes = "this will add new signal id but in memory and reset once server restarted")
    public ResponseEntity<Object> addSignals(@RequestBody Signal signal,
                                             @PathVariable("signalId") String signalId,
                                             HttpServletRequest request, HttpServletResponse response) throws IOException {

        List<String> errors = modelValidation.verifyModel(signal);
        if (errors.size() == 0) {
            signalDataService.add(signalId, signal);
            return ResponseEntity.status(HttpStatus.CREATED).body(signal);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);

        }
    }


}