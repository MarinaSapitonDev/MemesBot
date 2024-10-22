package com.sapiton.memesbot.util.actions;

import java.util.HashMap;
import java.util.Map;

import static com.sapiton.memesbot.util.Buttons.*;

public class ButtonsActionsFactory {
    private static final Map<String, ButtonsAction> actions = new HashMap<>();

    static {
        actions.put(ADD.name(),new Add());
        actions.put(FIND.name(),new Find());
        actions.put(SHOW.name(),new Show());
    }

    public static ButtonsAction getAction(String buttonType){
        ButtonsAction action = actions.get(buttonType);
        if(action==null)
            throw new IllegalArgumentException();
        return action;
    }
}
