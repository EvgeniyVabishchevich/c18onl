package com.tms.webshop.model.enums;

public enum RequestParams {
    COMMAND("command"),
    IMAGE("image"),
    PRODUCT_ID("productId"),
    IMAGE_NAME("imageName"),
    NAME("name"),
    Price("price"),
    CATEGORY("category"),
    CATEGORY_NAME("categoryName"),
    PASSWORD("password"),
    LOGIN("login"),
    BIRTHDAY("birthday"),
    PASSWORD_REPEAT("passwordRepeat"),
    EMAIL("email"),
    SURNAME("surname"),
    DESCRIPTION("description");


    private final String value;

    RequestParams(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
