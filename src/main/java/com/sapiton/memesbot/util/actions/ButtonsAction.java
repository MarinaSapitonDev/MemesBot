package com.sapiton.memesbot.util.actions;

import com.sapiton.memesbot.util.Button;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

public interface ButtonsAction {
    List<Button> doAction(Update update);
}
