package com.example.memesbot.util.actions;

import org.telegram.telegrambots.meta.api.objects.Update;

public class Add implements ButtonsAction {

    @Override
    public String doAction(Update update) {
        return "folder created";
    }
}
