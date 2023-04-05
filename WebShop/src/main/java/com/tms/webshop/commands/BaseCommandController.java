package com.tms.webshop.commands;

import com.tms.webshop.model.enums.Pages;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface BaseCommandController {
    Pages execute(HttpServletRequest request, HttpServletResponse response);
}
