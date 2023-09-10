package com.db.task.assignment.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "operation",
        "param",
        "value"
})

public class Action {

    @JsonProperty("operation")
    private String operation;
    @JsonProperty("param")
    private Integer param;
    @JsonProperty("value")
    private Integer value;


    @JsonProperty("operation")
    public String getOperation() {
        return operation;
    }

    @JsonProperty("operation")
    public void setOperation(String operation) {
        this.operation = operation;
    }

    @JsonProperty("param")
    public Integer getParam() {
        return param;
    }

    @JsonProperty("param")
    public void setParam(Integer param) {
        this.param = param;
    }

    @JsonProperty("value")
    public Integer getValue() {
        return value;
    }

    @JsonProperty("value")
    public void setValue(Integer value) {
        this.value = value;
    }

    @AssertTrue(message = "param and value must be provided when operation is setAlgoParam otherwise not")
    private boolean isParamAndValueValid() {
        if ("setAlgoParam".equals(operation)) {
            return param != null && value != null;
        }

        return param == null && value == null;


    }

}