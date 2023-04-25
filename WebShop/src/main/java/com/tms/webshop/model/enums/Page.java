package com.tms.webshop.model.enums;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public enum Page {
    ADMIN("admin/admin"),
    BUY("buy"),
    CART("cart"),
    CATEGORIES("categories"),
    ERROR404("error404"),
    LOGIN("login"),
    REGISTER("register"),
    PRODUCTS("products"),
    USER("user"),
    SEARCH_RESULT("searchResult"),
    SEARCH("search");

    private static final Map<String, Page> pagesMap = new ConcurrentHashMap<>();

    private final String value;

    static {
        for (Page page : values()) {
            pagesMap.put(page.getValue(), page);
        }
    }

    public static Page fromString(String type) {
        return Optional.ofNullable(pagesMap.get(type)).orElseThrow(() -> new IllegalStateException("No such page." + type));
    }

    Page(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
