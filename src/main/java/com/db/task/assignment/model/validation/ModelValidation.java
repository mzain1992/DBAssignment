package com.db.task.assignment.model.validation;

import com.db.task.assignment.model.Action;
import com.db.task.assignment.model.Signal;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class ModelValidation {
    ValidatorFactory factory;
    Validator validator;

    @PostConstruct
    void construct() {
        factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();


    }

    public List<String> verifyModel(Signal signal) {
        List<String> errors = new ArrayList<>();

        signal.getActions().forEach(action -> {
            Set<ConstraintViolation<Action>> violations = validator.validate(action);

            if (!violations.isEmpty()) {
                // Handle validation errors
                for (ConstraintViolation<Action> violation : violations) {
                    errors.add(violation.getMessage());
                }
            }

        });


        return errors;
    }
}
