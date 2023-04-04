package com.tms.webshop.model.enums;

public enum Commands {
    SIGN_IN("sign-in"),
    REGISTER("register"),
    BUY("buy"),
    ADD_PRODUCT_CART("add-product-cart"),
    CART("cart"),
    IMAGE("image"),
    PRODUCTS("products"),
    USER("user"),
    REMOVE_FROM_CART("remove-from-cart"),
    NEW_CATEGORY("new-category"),
    NEW_PRODUCT("new-product");

    private final String command;

    Commands(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }
}
