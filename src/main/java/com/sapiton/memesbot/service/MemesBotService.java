package com.sapiton.memesbot.service;

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

    public String getFileId(Update update) {
        List<PhotoSize> photos = update.getMessage().getPhoto();
        // Select the highest resolution photo (the last one in the list)
        return photos.get(photos.size() - 1).getFileId();

    }

    public void setKeyBoard(SendMessage message){

        InlineKeyboardButton buttonAdd = new InlineKeyboardButton();
        buttonAdd.setText(ADD.name());
        buttonAdd.setCallbackData(ADD.name());

        InlineKeyboardButton buttonFind = new InlineKeyboardButton();
        buttonFind.setText(FIND.name());
        buttonFind.setCallbackData(FIND.name());

        InlineKeyboardButton buttonShow = new InlineKeyboardButton();
        buttonShow.setText(SHOW.name());
        buttonShow.setCallbackData(SHOW.name());


        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> keyboardFirstRow = new ArrayList<>();
        List<InlineKeyboardButton> keyboardSecondRow = new ArrayList<>();

        keyboardFirstRow.add(buttonAdd);
        keyboardFirstRow.add(buttonFind);
        keyboardSecondRow.add(buttonShow);

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardFirstRow);
        rowList.add(keyboardSecondRow);

        inlineKeyboardMarkup.setKeyboard(rowList);
        message.setReplyMarkup(inlineKeyboardMarkup);
    }
}