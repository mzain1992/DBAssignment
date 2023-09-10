package com.db.task.assignment.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"actions"
})

public class Signal {

@JsonProperty("actions")
private List<Action> actions;


@JsonProperty("actions")
public List<Action> getActions() {
return actions;
}

@JsonProperty("actions")
public void setActions(List<Action> actions) {
this.actions = actions;
}


}