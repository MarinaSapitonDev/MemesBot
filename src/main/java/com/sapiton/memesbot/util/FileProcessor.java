package com.sapiton.memesbot.util;

import com.sapiton.memesbot.config.BotConfig;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

@Component
public class FileProcessor {

    private final BotConfig botConfig;

    @Autowired
    public FileProcessor(BotConfig botConfig) {
        this.botConfig = botConfig;
    }

    private static final String path = "./photos/";
    private static final String extension = ".jpg";
    private static final String url = "https://api.telegram.org/file/bot";

    public void save(String fileId, org.telegram.telegrambots.meta.api.objects.File file) throws IOException {
        InputStream is = new URL(url + botConfig.getToken() + "/" + file.getFilePath()).openStream();
        File localFile = new File(path + fileId + extension);
        FileUtils.copyInputStreamToFile(is, localFile);
        is.close();

        //TODO add logs System.out.println("uploaded");
    }
}
