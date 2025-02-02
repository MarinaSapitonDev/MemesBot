package com.sapiton.memesbot.util.actions;

import com.sapiton.memesbot.service.MemesBotService;
import com.sapiton.memesbot.util.Button;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Component
public class CreateCategory implements ButtonsAction{
    public MemesBotService service;

    @Override
    public List<Button> doAction(Update update) {
        List<Button> buttons = new ArrayList<>();
        //TODO should create category by writing its name in the text chat
        return buttons;
    }
}
