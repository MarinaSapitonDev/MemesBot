package com.sapiton.memesbot;

import com.sapiton.memesbot.config.BotConfig;
import com.sapiton.memesbot.service.MemesBotService;
import com.sapiton.memesbot.util.FileProcessor;
import com.sapiton.memesbot.util.actions.ButtonsAction;
import com.sapiton.memesbot.util.actions.ButtonsActionsFactory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.GetFile;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

@Component
@AllArgsConstructor
public class MemesBot extends TelegramLongPollingBot {

    private final BotConfig botConfig;
    private final MemesBotService service;
    private final FileProcessor fileProcessor;

    @Override
    public String getBotUsername() {
        return botConfig.getBotName();
    }

    @Override
    public String getBotToken() {
        return botConfig.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            handleMessage(update);
        } else if (update.hasCallbackQuery()) {
            handleCallbackQuery(update);
        }
    }

    private void handleMessage(Update update) {

        Map<String, Consumer<Update>> messageHandler = new HashMap();
        messageHandler.put("text", this::handleText);
        messageHandler.put("photo", this::handlePhoto);

        if (update.getMessage().hasText()) {
            messageHandler.getOrDefault("text", this::handleUnknownMessage).accept(update);
        } else if (update.getMessage().hasPhoto()) {
            messageHandler.getOrDefault("photo", this::handleUnknownMessage).accept(update);
        }
    }

    private void handleUnknownMessage(Update update) {
        long chatId = update.getMessage().getChatId();
        sendDefaultImage(chatId);
    }

    private void handleText(Update update) {
        String messageText = update.getMessage().getText();
        long chatId = update.getMessage().getChatId();

        Map<String, Runnable> commands = new HashMap<>();
        commands.put("/start", () -> startCommandReceived(chatId, update.getMessage().getChat().getFirstName()));
        commands.put("/help", () -> sendHelpMessage(chatId));
        commands.getOrDefault(messageText, () -> sendDefaultImage(chatId)).run();
    }

    private void handleCallbackQuery(Update update) {
        try {

            SendMessage sendMessage = new SendMessage();
            sendMessage.setText(update.getCallbackQuery().getData());
            sendMessage.setChatId(update.getCallbackQuery().getMessage().getChatId());

            ButtonsAction action = ButtonsActionsFactory.getAction(update.getCallbackQuery().getData());
            sendMessage.setText(action.doAction(update));

            execute(sendMessage);

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void handlePhoto(Update update) {
        String fileId = service.getFileId(update);
        try {
            uploadImage(fileId);
            sendImage(update.getMessage().getChatId(), fileId);

        } catch (IOException | TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    public void startCommandReceived(long chatId, String firstName) {
        sendGreetingImage(chatId);
        sendMessage(chatId, "Hi " + firstName +" Welcome to your favorite memes");
        sendStartKeyboard(chatId);
    }

    private void sendHelpMessage(long chatId) {
        sendMessage(chatId, " How can I help?");
    }

    private void sendMessage(long chatId, String textToSend) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(textToSend);


        try {
            execute(message);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    private void sendStartKeyboard(long chatId){
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText("Choose further action");
        service.setKeyBoard(message);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    private void sendImage(long chatId, String fileId) {
        SendPhoto sendPhotoRequest = new SendPhoto();
        sendPhotoRequest.setChatId(chatId);

        sendPhotoRequest.setParseMode("HTML");
        sendPhotoRequest.setCaption("your favorite meme");
        sendPhotoRequest.setPhoto(new InputFile(new File("./photos/" + fileId + ".jpg")));

        try {
            execute(sendPhotoRequest);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void sendGreetingImage(long chatId){
        SendPhoto sendPhotoRequest = new SendPhoto();
        sendPhotoRequest.setChatId(chatId);

        sendPhotoRequest.setParseMode("HTML");
        sendPhotoRequest.setPhoto(new InputFile(new File("greet.jpg")));

        try {
            execute(sendPhotoRequest);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void sendDefaultImage(long chatId) {
        SendPhoto sendPhotoRequest = new SendPhoto();
        sendPhotoRequest.setChatId(chatId);

        sendPhotoRequest.setParseMode("HTML");
        sendPhotoRequest.setCaption("Лох");
        sendPhotoRequest.setPhoto(new InputFile(new File("image.jpg")));

        try {
            execute(sendPhotoRequest);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void uploadImage(String file_id) throws IOException, TelegramApiException {

        final GetFile getFileMethod = new GetFile();
        getFileMethod.setFileId(file_id);

        org.telegram.telegrambots.meta.api.objects.File file = execute(getFileMethod);
        fileProcessor.save(file_id, file);
    }
}

