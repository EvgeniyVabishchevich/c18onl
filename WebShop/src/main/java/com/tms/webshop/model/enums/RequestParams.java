package com.tms.webshop.model.enums;

public enum RequestParams {
    COMMAND("command"),
    IMAGE("image");

    private final String value;

    RequestParams(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
