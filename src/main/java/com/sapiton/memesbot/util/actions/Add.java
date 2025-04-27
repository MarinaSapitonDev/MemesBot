package com.sapiton.memesbot.util.actions;

import com.sapiton.memesbot.service.MemesBotService;
import com.sapiton.memesbot.util.Button;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.ArrayList;
import java.util.List;

import static com.sapiton.memesbot.util.Buttons.*;

@AllArgsConstructor
@Component
public class Add implements ButtonsAction {
    @Override
    public List<Button> doAction(Update update) {
        List<Button> buttons = new ArrayList<>();
        buttons.add(new Button(CREATE_CATEGORY.getText(),CREATE_CATEGORY.getText() ));
        buttons.add(new Button(CHOOSE_CATEGORY.getText(),CHOOSE_CATEGORY.getText()));
        return buttons;
    }
}
