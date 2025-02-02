package com.sapiton.memesbot.util.actions;

import com.sapiton.memesbot.service.MemesBotService;
import com.sapiton.memesbot.util.Button;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

@AllArgsConstructor
@Component
public class Show implements ButtonsAction {
    MemesBotService service;
    @Override
    public List<Button> doAction(Update update) {
       return null;
    }
}
