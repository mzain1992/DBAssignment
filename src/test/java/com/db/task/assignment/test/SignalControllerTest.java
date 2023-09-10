package com.db.task.assignment.test;

/**
 * Uses JsonPath: http://goo.gl/nwXpb, Hamcrest and MockMVC
 */

import com.db.task.assignment.Application;
import com.db.task.assignment.api.rest.v1.SignalController;
import com.db.task.assignment.model.Action;
import com.db.task.assignment.model.Signal;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;


import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class SignalControllerTest {

    @InjectMocks
    SignalController controller;

    @Autowired
    WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void initTests() {
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }


    @Test
    public void testNewSignalAdd() throws Exception {


        Signal r1 = MockSignal("setAlgoParam");
        byte[] r1Json = toJson(r1);

        //Add new signal operations
        mvc.perform(post("/tradingApplication/v1/signal/" + 9)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(r1Json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.actions[0].operation", is("setAlgoParam")))
                .andExpect(jsonPath("$.actions[0].param", is(1)))
                .andExpect(jsonPath("$.actions[0].value", is(3)));

    }


    @Test
    public void testNewSignalAddError() throws Exception {


        Signal r1 = MockSignal("calc");
        byte[] r1Json = toJson(r1);

        //Add new signal operations
        mvc.perform(post("/tradingApplication/v1/signal/" + 9)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(r1Json))
                .andExpect(status().is4xxClientError())
                 .andExpect(jsonPath("$.[0]", is("param and value must be provided when operation is setAlgoParam otherwise not")));

    }

    @Test
    public void testSignal() throws Exception {

       //process signal
        mvc.perform(get("/tradingApplication/v1/signal/" + 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();



    }

    private Signal MockSignal(String operation) {
        Signal signal = new Signal();
        List<Action> actionList = new ArrayList<>();
        Action r = new Action();
        r.setOperation(operation);
        r.setParam(1);
        r.setValue(3);
        actionList.add(r);


        signal.setActions(actionList);
        return signal;
    }

    private byte[] toJson(Object r) throws Exception {
        ObjectMapper map = new ObjectMapper();
        return map.writeValueAsString(r).getBytes();
    }


}
