package com.sapiton.memesbot.config;

import com.sapiton.memesbot.service.MemesBotService;
import com.sapiton.memesbot.util.actions.*;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

import static com.sapiton.memesbot.util.Buttons.*;

@Configuration
@AllArgsConstructor
public class ActionsConfig {

    private final Add addAction;

    private final Find findAction;

    private final Show showAction;

    private final ChooseCategory chooseCategoryAction;

    private final CreateCategory createCategoryAction;
    //TODO change names of actions so it could be found
    @Bean
    public Map<String, ButtonsAction> actions() {
        Map<String, ButtonsAction> actions = new HashMap<>();
        actions.put(ADD.getText(), addAction);
        actions.put(FIND.getText(), findAction);
        actions.put(SHOW.getText(), showAction);
        actions.put(CHOOSE_CATEGORY.getText(), chooseCategoryAction);
        actions.put(CREATE_CATEGORY.getText(), createCategoryAction);

        // Add other actions here
        return actions;
    }

    @Bean
    public Add addAction(MemesBotService service) {
        return new Add(service);
    }

    @Bean
    public Find findAction(MemesBotService service) {
        return new Find(service);
    }

    @Bean
    public Show showAction(MemesBotService service) {
        return new Show(service);
    }

    @Bean
    public ChooseCategory chooseCategoryAction(MemesBotService service) {
        return new ChooseCategory(service);
    }

    @Bean
    public CreateCategory createCategoryAction(MemesBotService service) {
        return new CreateCategory(service);
    }
}