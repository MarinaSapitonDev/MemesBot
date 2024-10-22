package com.example.memesbot.util.actions;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface ButtonsAction {
    String doAction(Update update);
}
