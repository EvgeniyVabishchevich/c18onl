package com.tms.webshop.model.enums;

public enum Pages {
    ADMIN("admin.jsp"),
    BUY("buy.jsp"),
    CART("cart.jsp"),
    CATEGORIES("categories.jsp"),
    ERROR404("error404.jsp"),
    LOGIN("login.jsp"),
    REGISTER("newAccount.jsp"),
    PRODUCTS("products.jsp"),
    USER("user.jsp"),
    CURRENT("");

    private final String value;

    Pages(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
