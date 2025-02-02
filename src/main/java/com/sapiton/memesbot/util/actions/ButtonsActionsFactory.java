package com.sapiton.memesbot.util.actions;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;


@Component
@AllArgsConstructor
public class ButtonsActionsFactory {
    private final Map<String, ButtonsAction> actions;

    public ButtonsAction getAction(String buttonType) {
        actions.forEach((key, value) -> System.out.println("Key: " + key + ", Value: " + value.getClass().getName()));
        ButtonsAction action = actions.get(buttonType);
        if (action == null)
            throw new IllegalArgumentException("Action not found for button type: " + buttonType);
        return action;
    }
}
