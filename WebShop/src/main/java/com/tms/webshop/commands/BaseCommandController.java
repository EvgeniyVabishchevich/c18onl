package com.tms.webshop.commands;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface BaseCommandController {
    String execute(HttpServletRequest request, HttpServletResponse response);
}
