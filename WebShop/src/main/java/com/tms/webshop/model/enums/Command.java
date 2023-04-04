package com.tms.webshop.model.enums;

import java.util.HashMap;
import java.util.Optional;

public enum Command {
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

    private static final HashMap<String, Command> commandMap = new HashMap<>();

    static {
        for (Command type : values()) {
            commandMap.put(type.getCommand(), type);
        }
    }

    public static Command fromString(String type) {
        return Optional.ofNullable(commandMap.get(type)).orElseThrow(() -> new IllegalStateException("No such command."));
    }

    private final String command;

    Command(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }
}
