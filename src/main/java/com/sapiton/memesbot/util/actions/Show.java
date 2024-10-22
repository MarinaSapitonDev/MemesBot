package com.sapiton.memesbot.util.actions;

import org.telegram.telegrambots.meta.api.objects.Update;

public class Show implements ButtonsAction {
    @Override
    public String doAction(Update update) {
        return "Show folders";
    }
}
