package com.sapiton.memesbot.util.actions;

import org.telegram.telegrambots.meta.api.objects.Update;

public class Add implements ButtonsAction {

    @Override
    public String doAction(Update update) {
        //TODO add code to create folders like buttons: add buttons new category and existing category
        return "create new category or add to existed";
    }
}
