package com.sapiton.memesbot.util;

import lombok.Getter;

@Getter
public enum Buttons {

    ADD("add"),
    FIND("find"),
    SHOW("show"),
    CHOOSE_CATEGORY("choose"),
    CREATE_CATEGORY("create");

    private final String text;

    Buttons(String text) {
        this.text = text;
    }
}
