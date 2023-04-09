package com.tms.webshop.model.enums;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public enum Page {
    ADMIN("admin/admin.jsp"),
    BUY("buy.jsp"),
    CART("cart.jsp"),
    CATEGORIES("categories.jsp"),
    ERROR404("error404.jsp"),
    LOGIN("login.jsp"),
    REGISTER("newAccount.jsp"),
    PRODUCTS("products.jsp"),
    USER("user.jsp"),
    SEARCH_RESULT("searchResult.jsp"),
    CURRENT("");

    private static final Map<String, Page> pagesMap = new ConcurrentHashMap<>();

    private final String value;

    static {
        for (Page page : values()) {
            pagesMap.put(page.getValue(), page);
        }
    }

    public static Page fromString(String type) {
        return Optional.ofNullable(pagesMap.get(type)).orElseThrow(() -> new IllegalStateException("No such page."));
    }

    Page(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
