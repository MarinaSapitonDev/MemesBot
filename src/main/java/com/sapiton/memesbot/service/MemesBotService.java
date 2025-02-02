package com.sapiton.memesbot.service;

import com.sapiton.memesbot.util.actions.Button;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

import static com.sapiton.memesbot.util.Buttons.*;

@Service
public class MemesBotService {

    public static final int BUTTONS_PER_ROW = 2;

    public String getFileId(Update update) {
        List<PhotoSize> photos = update.getMessage().getPhoto();
        // Select the highest resolution photo (the last one in the list)
        return photos.get(photos.size() - 1).getFileId();
    }

    public void setKeyBoard(SendMessage message) {
        createButtons(message, initializeButtons());
    }

    public List<Button> initializeButtons(){
        List<Button> buttons = new ArrayList<>();
        buttons.add(new Button(ADD.name(), ADD.name()));
        buttons.add(new Button(FIND.name(), FIND.name()));
        buttons.add(new Button(SHOW.name(), SHOW.name()));
        return buttons;
    }

    public void createButtons(SendMessage message, List<Button> buttons) {

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        List<InlineKeyboardButton> row = new ArrayList<>();

        for (int i = 0; i < buttons.size(); i++) {
            Button button = buttons.get(i);
            InlineKeyboardButton telegramButton = new InlineKeyboardButton();
            telegramButton.setText(button.getText());
            telegramButton.setCallbackData(button.getName());
            row.add(telegramButton);

            if ((i + 1) % BUTTONS_PER_ROW == 0 || i == buttons.size() - 1) {
                rowList.add(row);
                row = new ArrayList<>();
            }
        }

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(rowList);
        message.setReplyMarkup(inlineKeyboardMarkup);
    }
    //TODO ask Vitalij to review the code
}